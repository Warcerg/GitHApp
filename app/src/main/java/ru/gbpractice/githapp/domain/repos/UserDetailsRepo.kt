package ru.gbpractice.githapp.domain.repos

import io.reactivex.rxjava3.core.Single
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

interface UserDetailsRepo {

    fun getUserDetails(login : String) : Single<UserDetailsEntity>

    fun getUserRepoList(login : String) : Single<List<UserRepoEntity>>
}