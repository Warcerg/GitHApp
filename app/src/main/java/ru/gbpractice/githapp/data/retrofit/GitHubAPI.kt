package ru.gbpractice.githapp.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.gbpractice.githapp.data.retrofit.entitiesDTO.UserEntityDTO
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

interface GitHubAPI {

    @GET("users")
    fun getUsersList(): Single<List<UserEntityDTO>>

    @GET("users/{login}/repos")
    fun getUserRepoList(
        @Path("login") login: String
    ): Single<List<UserRepoEntity>>


    @GET("users/{login}")
    fun getUserInfo(
        @Path("login") login: String
    ): Single<UserDetailsEntity>
}