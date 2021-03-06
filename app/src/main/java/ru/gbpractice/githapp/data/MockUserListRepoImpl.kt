package ru.gbpractice.githapp.data

import io.reactivex.rxjava3.core.Single
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.repos.UserListRepo

private const val DATA_FAKE_LOAD_DELAY = 2_000L

class MockUserListRepoImpl : UserListRepo {

    private val data: List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntity("wycats", 4, "https://avatars.githubusercontent.com/u/4?v=4"),
        UserEntity("ezmobius", 5, "https://avatars.githubusercontent.com/u/5?v=4")
    )

    override fun getUserList(): Single<List<UserEntity>> = Single.just(data)
}