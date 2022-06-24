package ru.gbpractice.githapp.domain.repos

import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

interface UserDetailsRepo {

    fun getUserDetails(
        login: String,
        onSuccess: (UserDetailsEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    )

    fun getUserRepoList(
        login: String,
        onSuccess: (List<UserRepoEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}