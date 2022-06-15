package ru.gbpractice.githapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gbpractice.githapp.data.MockUserListRepoImpl
import ru.gbpractice.githapp.data.RetrofitUserListRepoImpl
import ru.gbpractice.githapp.domain.repos.UserListRepo

class App: Application() {
    val userListRepo: UserListRepo by lazy { RetrofitUserListRepoImpl() }
}

val Context.app :  App get() = applicationContext as App
val Fragment.app : App get() = requireContext().applicationContext as App