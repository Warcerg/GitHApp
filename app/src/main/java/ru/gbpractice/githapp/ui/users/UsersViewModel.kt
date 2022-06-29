package ru.gbpractice.githapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.repos.UserListRepo

class UsersViewModel(
    private val userListRepo: UserListRepo
    ) : UsersContract.ViewModel {

    override val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = MutableLiveData()
    override val loadingLiveData: LiveData<Boolean> = MutableLiveData()
    override val showUserDetailsLiveData: LiveData<UserEntity> = MutableLiveData()


    override fun onRefreshUserList() {
        loadingLiveData.mutable().postValue(true)
        userListRepo.getUserList(
            onSuccess = {
                loadingLiveData.mutable().postValue(false)
                usersLiveData.mutable().postValue(it)
            },
            onError = {
                loadingLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    override fun onSelectUser(userEntity: UserEntity) {
        showUserDetailsLiveData.mutable().postValue(userEntity)
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not mutableLiveData")
    }
}