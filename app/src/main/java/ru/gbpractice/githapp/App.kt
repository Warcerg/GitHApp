package ru.gbpractice.githapp

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.data.RetrofitUserDetailsRepoImpl
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.di.appModule
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.domain.repos.UserListRepo


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }

    companion object {
        const val BUNDLE_KEY = "login"
    }
}

val Context.app: App get() = applicationContext as App

