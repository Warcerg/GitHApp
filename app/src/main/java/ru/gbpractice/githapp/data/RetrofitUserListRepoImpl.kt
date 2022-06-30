package ru.gbpractice.githapp.data


import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.domain.repos.UserListRepo


class RetrofitUserListRepoImpl : UserListRepo {

    private val baseUrl = "https://api.github.com/"

    private val gitAPI: GitHubAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        adapter.create(GitHubAPI::class.java)
    }

    override fun getUserList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        gitAPI.getUsersList().subscribeBy(
            onSuccess = { users ->
                onSuccess.invoke(users.map { it.toUserEntity() })
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

}

