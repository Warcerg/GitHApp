package ru.gbpractice.githapp.ui.details

import ru.gbpractice.githapp.domain.entities.UserEntity

interface DetailsContract {
    interface View {
        fun showRepoList()
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