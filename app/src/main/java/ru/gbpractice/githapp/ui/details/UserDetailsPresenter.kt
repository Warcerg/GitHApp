package ru.gbpractice.githapp.ui.details

import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo

class UserDetailsPresenter(private val userDetailsRepo: UserDetailsRepo) :
    DetailsContract.Presenter {

    private var view: DetailsContract.View? = null
    private var userEntity: UserEntity? = null
    private var userDetails: UserDetailsEntity? = null
    private var repoList: List<UserRepoEntity>? = null
    private var isLoading: Boolean = false


    override fun attach(view: DetailsContract.View, userEntity: UserEntity) {
        this.view = view
        this.view?.showLoading(isLoading)
        if (this.userEntity?.equals(userEntity) == true) {
            this.view?.let { restoreDetailsData(it) }
        } else {
            provideUserData(userEntity)
            provideUserDetails(userEntity)
            loadRepoList(userEntity)
        }
    }

    private fun restoreDetailsData(view: DetailsContract.View) {
        userEntity?.let { view.showUser(it) }
        userDetails?.let { view.showUserDetails(it) }
        repoList?.let { view.showRepoList(it) }
    }

    override fun detach() {
        view = null
    }

    override fun provideUserData(userEntity: UserEntity) {
        this.userEntity = userEntity
        view?.showUser(userEntity)
    }

    override fun provideUserDetails(userEntity: UserEntity) {
        isLoading = true
        view?.showLoading(isLoading)
        userDetailsRepo.getUserDetails(
            userEntity.login,
            onSuccess = {
                isLoading = false
                view?.showLoading(isLoading)
                userDetails = it
                view?.showUserDetails(it)
            },
            onError = {
                isLoading = false
                view?.showLoading(isLoading)
                view?.showError(it)
            }
        )
    }

    override fun loadRepoList(userEntity: UserEntity) {
        isLoading = true
        view?.showLoading(isLoading)
        userDetailsRepo.getUserRepoList(
            userEntity.login,
            onSuccess = {
                isLoading = false
                view?.showLoading(isLoading)
                repoList = it
                view?.showRepoList(it)
            },
            onError = {
                isLoading = false
                view?.showLoading(isLoading)
                view?.showError(it)
            }
        )
    }
}