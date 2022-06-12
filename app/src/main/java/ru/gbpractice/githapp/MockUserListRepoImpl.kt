package ru.gbpractice.githapp

class MockUserListRepoImpl: UserListRepo{

    private val data: List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntity("wycats", 4, "https://avatars.githubusercontent.com/u/4?v=4"),
        UserEntity("ezmobius", 5, "https://avatars.githubusercontent.com/u/5?v=4")
    )

    override fun getUserList() = data
}