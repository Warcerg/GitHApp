package ru.gbpractice.githapp

import android.app.Application
import android.content.Context
import ru.gbpractice.githapp.di.DiModule


class App : Application() {

    init {
        DiModule()
    }

    companion object {
        const val BUNDLE_KEY = "login"
    }
}

val Context.app: App get() = applicationContext as App

