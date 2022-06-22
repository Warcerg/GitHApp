package ru.gbpractice.githapp.ui.details

import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

interface DetailsContract {
    interface View {

        fun showUserDetails(details: UserDetailsEntity)
        fun showRepoList(repos: List<UserRepoEntity>)
        fun showLoading(isLoading: Boolean)
        fun showError(t: Throwable)

    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun provideUserDetails(userEntity: UserEntity)
        fun loadRepoList(userEntity: UserEntity)

    }
}