package ru.gbpractice.githapp

import android.app.Application
import android.content.Context
import ru.gbpractice.githapp.data.RetrofitUserDetailsRepoImpl
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.domain.repos.UserListRepo


class App : Application() {

    val userListRepo: UserListRepo by lazy { RetrofitUserListRepoImpl() }
    val userDetailsRepo: UserDetailsRepo by lazy { RetrofitUserDetailsRepoImpl() }


    companion object {
        const val BUNDLE_KEY = "login"
    }
}

val Context.app: App get() = applicationContext as App

