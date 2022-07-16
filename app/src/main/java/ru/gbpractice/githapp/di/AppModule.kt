package ru.gbpractice.githapp.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.data.RetrofitUserDetailsRepoImpl
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.domain.repos.UserListRepo

@Module
class AppModule {

    @Provides
    fun provideBaseURL(): String {
        return "https://api.github.com/"
    }

    @Provides
    fun provideRetrofit(baseUrl: String): GitHubAPI {
        val adapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return adapter.create(GitHubAPI::class.java)
    }

    @Provides
    fun provideUserListRepo(
        api: GitHubAPI
    ): UserListRepo {
        return RetrofitUserListRepoImpl(api)
    }

    @Provides
    fun provideUserDetailsRepo(
        api: GitHubAPI
    ): UserDetailsRepo {
        return RetrofitUserDetailsRepoImpl(api)
    }

}

