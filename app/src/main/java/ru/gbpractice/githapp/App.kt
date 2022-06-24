package ru.gbpractice.githapp

import android.app.Application
import android.content.Context
import ru.gbpractice.githapp.data.RetrofitUserDetailsRepoImpl
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.domain.repos.UserListRepo
import ru.gbpractice.githapp.ui.details.UserDetailsPresenter
import ru.gbpractice.githapp.ui.users.UsersContract
import ru.gbpractice.githapp.ui.users.UsersPresenter

class App : Application() {

    private val userListRepo: UserListRepo by lazy { RetrofitUserListRepoImpl() }
    private val userDetailsRepo: UserDetailsRepo by lazy { RetrofitUserDetailsRepoImpl() }
    val userListPresenter: UsersContract.Presenter by lazy { UsersPresenter(userListRepo) }
    val userDetailsPresenter: UserDetailsPresenter by lazy { UserDetailsPresenter(userDetailsRepo) }

    companion object {
        const val BUNDLE_KEY = "login"
    }
}

val Context.app: App get() = applicationContext as App

