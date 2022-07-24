package ru.gbpractice.githapp

import android.app.Application
import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.data.RetrofitUserDetailsRepoImpl
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.domain.repos.UserListRepo

class App : Application() {

    private val baseUrl = "https://api.github.com/"

    private val gitAPI: GitHubAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        adapter.create(GitHubAPI::class.java)
    }

    val userListRepo: UserListRepo by lazy { RetrofitUserListRepoImpl(gitAPI) }
    val userDetailsRepo: UserDetailsRepo by lazy { RetrofitUserDetailsRepoImpl(gitAPI) }

    companion object {
        const val BUNDLE_KEY = "login"
    }
}

val Context.app: App get() = applicationContext as App

