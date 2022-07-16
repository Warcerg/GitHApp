package ru.gbpractice.githapp.di

import dagger.Component
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.domain.repos.UserListRepo

@Component(modules = [AppModule::class])
interface AppComponent {

    fun getUserListRepo(): UserListRepo

    fun getUserDetailsRepo(): UserDetailsRepo
}