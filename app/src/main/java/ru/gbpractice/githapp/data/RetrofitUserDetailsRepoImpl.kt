package ru.gbpractice.githapp.data

import io.reactivex.rxjava3.core.Single
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo


class RetrofitUserDetailsRepoImpl (
    private val gitAPI: GitHubAPI
    ) : UserDetailsRepo {

    override fun getUserDetails(login: String): Single<UserDetailsEntity> = gitAPI.getUserInfo(login)

    override fun getUserRepoList(login: String): Single<List<UserRepoEntity>> = gitAPI.getUserRepoList(login)

}