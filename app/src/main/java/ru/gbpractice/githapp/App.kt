package ru.gbpractice.githapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.domain.repos.UserListRepo
import ru.gbpractice.githapp.ui.users.UsersContract
import ru.gbpractice.githapp.ui.users.UsersPresenter

class App: Application() {
    private val userListRepo: UserListRepo by lazy { RetrofitUserListRepoImpl() }
    val userListPresenter: UsersContract.Presenter by lazy {UsersPresenter(userListRepo)}
}

val Context.app :  App get() = applicationContext as App
val Fragment.app : App get() = requireContext().applicationContext as App