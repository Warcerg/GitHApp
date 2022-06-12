package ru.gbpractice.githapp

interface UserListRepo {

    fun getUserList(): List<UserEntity>
}