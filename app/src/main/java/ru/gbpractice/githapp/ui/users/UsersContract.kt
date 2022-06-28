package ru.gbpractice.githapp.ui.users

import androidx.lifecycle.LiveData
import ru.gbpractice.githapp.domain.entities.UserEntity

interface UsersContract {

    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val loadingLiveData: LiveData<Boolean>
        val showUserDetailsLiveData: LiveData<UserEntity>

        fun onRefreshUserList()
        fun onSelectUser(userEntity: UserEntity)
    }
}