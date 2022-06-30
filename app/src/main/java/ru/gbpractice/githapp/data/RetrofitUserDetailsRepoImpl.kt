package ru.gbpractice.githapp.data

import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo
import ru.gbpractice.githapp.data.retrofit.GitHubAPI


class RetrofitUserDetailsRepoImpl : UserDetailsRepo {

    private val baseUrl = "https://api.github.com/"

    private val gitAPI: GitHubAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        adapter.create(GitHubAPI::class.java)
    }

    override fun getUserDetails(
        login: String,
        onSuccess: (UserDetailsEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        gitAPI.getUserInfo(login).subscribeBy(
            onSuccess = {
                onSuccess.invoke(it)
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getUserRepoList(
        login: String,
        onSuccess: (List<UserRepoEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        gitAPI.getUserRepoList(login).subscribeBy(
            onSuccess = {
                onSuccess.invoke(it)
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }
}