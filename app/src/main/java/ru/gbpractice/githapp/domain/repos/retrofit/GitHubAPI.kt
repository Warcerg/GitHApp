package ru.gbpractice.githapp.domain.repos.retrofit

import retrofit2.Call
import retrofit2.http.GET
import ru.gbpractice.githapp.domain.entities.UserEntity

interface GitHubAPI {

    @GET("users")
    fun getUsersList(): Call<List<UserEntity>>
}