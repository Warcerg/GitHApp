package ru.gbpractice.githapp.domain.entities

import com.google.gson.annotations.SerializedName

data class UserRepoEntity (
    val name: String,
    val description: String,
    val language: String
)