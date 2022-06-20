package ru.gbpractice.githapp.ui.details

import ru.gbpractice.githapp.domain.entities.UserEntity

class UserDetailsPresenter(private val userDetailsPresenter: UserDetailsPresenter): DetailsContract.Presenter {
    override fun attach(view: DetailsContract.View) {
        TODO("Not yet implemented")
    }

    override fun detach() {
        TODO("Not yet implemented")
    }

    override fun provideUserDetails(userEntity: UserEntity) {
        TODO("Not yet implemented")
    }

    override fun loadRepoList(userEntity: UserEntity) {
        TODO("Not yet implemented")
    }
}