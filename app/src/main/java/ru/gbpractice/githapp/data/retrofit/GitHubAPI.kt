package ru.gbpractice.githapp.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

interface GitHubAPI {

    @GET("users")
    fun getUsersList(): Call<List<UserEntity>>

    @GET("users/{login}/repos")
    fun getUserRepoList(
        @Path("login") login: String
    ): Call<List<UserRepoEntity>>


    @GET("users/{login}")
    fun getUserInfo(
        @Path("login") login: String
    ): Call<UserDetailsEntity>
}