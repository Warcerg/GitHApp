package ru.gbpractice.githapp.data


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.repos.retrofit.GitHubAPI
import ru.gbpractice.githapp.domain.repos.UserListRepo


class RetrofitUserListRepoImpl : UserListRepo {

    private val baseUrl = "https://api.github.com/"

    private val gitAPI: GitHubAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        adapter.create(GitHubAPI::class.java)
    }

    override fun getUserList(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        gitAPI.getUsersList().enqueue(object : Callback<List<UserEntity>> {
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Error or no data available"))
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                onError?.invoke(t)
            }

        })

    }
}

