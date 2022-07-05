package ru.gbpractice.githapp.domain.repos

import io.reactivex.rxjava3.core.Single
import ru.gbpractice.githapp.domain.entities.UserEntity

interface UserListRepo {

    fun getUserList() : Single<List<UserEntity>>
}