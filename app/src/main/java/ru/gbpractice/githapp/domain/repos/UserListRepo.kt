package ru.gbpractice.githapp.domain.repos

import coil.request.SuccessResult
import ru.gbpractice.githapp.domain.entities.UserEntity

interface UserListRepo {

    fun getUserList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}