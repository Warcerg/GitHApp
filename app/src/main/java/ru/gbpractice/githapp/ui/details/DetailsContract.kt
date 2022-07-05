package ru.gbpractice.githapp.ui.details

import io.reactivex.rxjava3.core.Observable
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

interface DetailsContract {

    interface ViewModel{
        val userLiveData: Observable<UserEntity>
        val userDetailsLiveData: Observable<UserDetailsEntity>
        val userRepoListLiveData: Observable<List<UserRepoEntity>>
        val errorLiveData: Observable<Throwable>
        val loadingLiveData: Observable<Boolean>

        fun provideUserData(userEntity: UserEntity)
    }
}