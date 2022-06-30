package ru.gbpractice.githapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.utils.SingleLiveDataEvent

class UserDetailsViewModel(
    private val userDetailsRepo: UserDetailsRepo
) : DetailsContract.ViewModel {

    override val userLiveData: LiveData<UserEntity> = MutableLiveData()
    override val userDetailsLiveData: LiveData<UserDetailsEntity> = MutableLiveData()
    override val userRepoListLiveData: LiveData<List<UserRepoEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleLiveDataEvent()
    override val loadingLiveData: LiveData<Boolean> = MutableLiveData()


    override fun provideUserData(userEntity: UserEntity) {
        userLiveData.mutable().postValue(userEntity)
        provideUserDetails(userEntity)
        provideRepoList(userEntity)
    }

    private fun provideUserDetails(userEntity: UserEntity) {
        userDetailsRepo.getUserDetails(
            userEntity.login,
            onSuccess = {
                userDetailsLiveData.mutable().postValue(it)
            },
            onError = {
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun provideRepoList(userEntity: UserEntity) {
        loadingLiveData.mutable().postValue(true)
        userDetailsRepo.getUserRepoList(
            userEntity.login,
            onSuccess = {
                loadingLiveData.mutable().postValue(false)
                userRepoListLiveData.mutable().postValue(it)
            },
            onError = {
                loadingLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not mutableLiveData")
    }
}