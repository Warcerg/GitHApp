package ru.gbpractice.githapp.domain.entities


data class UserEntity (
    val login: String,
    val id: Long,
    val avatarUrl: String
)