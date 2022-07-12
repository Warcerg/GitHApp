package ru.gbpractice.githapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.data.RetrofitUserDetailsRepoImpl
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.domain.repos.UserListRepo
import ru.gbpractice.githapp.ui.details.UserDetailsViewModel
import ru.gbpractice.githapp.ui.users.UsersViewModel

val appModule = module {
    single(named("url")) { "https://api.github.com/" }

    single<GitHubAPI> {
        val adapter = Retrofit.Builder()
            .baseUrl(get<String>(named("url")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        adapter.create(GitHubAPI::class.java)
    }

    single<UserListRepo> { RetrofitUserListRepoImpl(get()) }
    single<UserDetailsRepo> { RetrofitUserDetailsRepoImpl(get()) }

    viewModel { UsersViewModel(get())}
    viewModel { UserDetailsViewModel(get())}
}

