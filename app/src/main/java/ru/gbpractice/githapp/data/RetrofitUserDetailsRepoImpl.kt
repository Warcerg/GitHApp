package ru.gbpractice.githapp.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
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
            .build()
        adapter.create(GitHubAPI::class.java)
    }

    override fun getUserDetails(
        login: String,
        onSuccess: (UserDetailsEntity) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        gitAPI.getUserInfo(login).enqueue(object : Callback<UserDetailsEntity> {
            override fun onResponse(
                call: Call<UserDetailsEntity>,
                response: Response<UserDetailsEntity>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Error or no USER_DETAILS data available"))
                }
            }
            override fun onFailure(call: Call<UserDetailsEntity>, t: Throwable) {
                onError?.invoke(t)
            }
        })
    }

    override fun getUserRepoList(
        login: String,
        onSuccess: (List<UserRepoEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        gitAPI.getUserRepoList(login).enqueue(object : Callback<List<UserRepoEntity>> {
            override fun onResponse(
                call: Call<List<UserRepoEntity>>,
                response: Response<List<UserRepoEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(java.lang.IllegalStateException("Error or no USER_REPOSITORIES data available"))
                }
            }
            override fun onFailure(call: Call<List<UserRepoEntity>>, t: Throwable) {
                onError?.invoke(t)
            }
        })
    }
}