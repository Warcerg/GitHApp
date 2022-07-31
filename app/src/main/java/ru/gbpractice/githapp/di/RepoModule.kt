package ru.gbpractice.githapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.data.RetrofitUserDetailsRepoImpl
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.domain.repos.UserListRepo

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindUserListRepo(
        retrofitUserListRepoImpl: RetrofitUserListRepoImpl
    ): UserListRepo

    @Binds
    abstract fun bindUserDetailsRepo(
        retrofitUserDetailsRepoImpl: RetrofitUserDetailsRepoImpl
    ): UserDetailsRepo

}

@Module
@InstallIn(SingletonComponent::class)
object GitApiModule {

    private val baseURL = "https://api.github.com/"

    @Provides
    fun provideRetrofit(): GitHubAPI {
        val adapter = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return adapter.create(GitHubAPI::class.java)
    }
}