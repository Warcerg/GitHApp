package ru.gbpractice.githapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment

class App: Application() {
    val userListRepo: UserListRepo by lazy {MockUserListRepoImpl()}
}

val Context.app :  App get() = applicationContext as App
val Fragment.app : App get() = requireContext().applicationContext as App