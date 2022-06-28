package ru.gbpractice.githapp.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import ru.gbpractice.githapp.App.Companion.BUNDLE_KEY
import ru.gbpractice.githapp.app
import ru.gbpractice.githapp.data.retrofit.entitiesDTO.UserEntityDTO
import ru.gbpractice.githapp.databinding.ActivityUserDetailsBinding
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity

class UserDetailsActivity : AppCompatActivity(), DetailsContract.View {

    private lateinit var binding: ActivityUserDetailsBinding
    private val adapter = UserReposAdapter()
    private lateinit var presenter: DetailsContract.Presenter
    private var userEntity: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        intent?.let {
            userEntity = intent.getParcelableExtra<UserEntityDTO>(BUNDLE_KEY)?.toUserEntity()
        }

        presenter = retainPresenter()
        presenter.attach(this, userEntity as UserEntity)
    }

    private fun retainPresenter(): DetailsContract.Presenter {
        return lastCustomNonConfigurationInstance as? DetailsContract.Presenter
            ?: UserDetailsPresenter(app.userDetailsRepo)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewRepositoryList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewRepositoryList.adapter = adapter
    }

    override fun showUser(userEntity: UserEntity) {
        binding.userLogin.text = userEntity.login
        binding.userId.text = userEntity.id.toString()
        binding.userAvatar.load(userEntity.avatarUrl)
    }

    override fun showUserDetails(details: UserDetailsEntity) {
        binding.userName.text = details.name
    }

    override fun showRepoList(repos: List<UserRepoEntity>) {
        adapter.setData(repos)
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.recyclerViewRepositoryList.isVisible = !isLoading
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }
}