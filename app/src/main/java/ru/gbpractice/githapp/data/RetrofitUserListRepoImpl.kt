package ru.gbpractice.githapp.data


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.data.retrofit.GitHubAPI
import ru.gbpractice.githapp.data.retrofit.entitiesDTO.UserEntityDTO
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
        gitAPI.getUsersList().enqueue(object : Callback<List<UserEntityDTO>> {
            override fun onResponse(
                call: Call<List<UserEntityDTO>>,
                response: Response<List<UserEntityDTO>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body.map {
                        it.toUserEntity()
                    })
                } else {
                    onError?.invoke(IllegalStateException("Error or no data available"))
                }
            }

            override fun onFailure(call: Call<List<UserEntityDTO>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }


}

