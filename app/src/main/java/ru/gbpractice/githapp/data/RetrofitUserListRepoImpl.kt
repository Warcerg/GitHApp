package ru.gbpractice.githapp.data


import io.reactivex.rxjava3.core.Single
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.repos.UserListRepo



class RetrofitUserListRepoImpl (
    private val gitAPI: GitHubAPI
) : UserListRepo {

    override fun getUserList(): Single<List<UserEntity>> = gitAPI.getUsersList()
        .map { users ->
            users.map {
                it.toUserEntity()
            }
        }

}

