package ru.gbpractice.githapp.ui.details

import androidx.lifecycle.LiveData
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

interface DetailsContract {

    interface ViewModel {
        val userLiveData: LiveData<UserEntity>
        val userDetailsLiveData: LiveData<UserDetailsEntity>
        val userRepoListLiveData: LiveData<List<UserRepoEntity>>
        val errorLiveData: LiveData<Throwable>
        val loadingLiveData: LiveData<Boolean>

        fun provideUserData(userEntity: UserEntity)
    }
}