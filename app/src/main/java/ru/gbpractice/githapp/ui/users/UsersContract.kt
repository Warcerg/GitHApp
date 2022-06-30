package ru.gbpractice.githapp.ui.users

import io.reactivex.rxjava3.core.Observable
import ru.gbpractice.githapp.domain.entities.UserEntity

interface UsersContract {

    interface ViewModel {
        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val loadingLiveData: Observable<Boolean>
        val showUserDetailsLiveData: Observable<UserEntity>

        fun onRefreshUserList()
        fun onSelectUser(userEntity: UserEntity)
    }
}