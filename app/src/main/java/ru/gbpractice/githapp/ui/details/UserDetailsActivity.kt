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

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    private val adapter = UserReposAdapter()
    private lateinit var viewModel: DetailsContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()

    }

    private fun initViewModel() {
        viewModel = getViewModel()

        intent.getParcelableExtra<UserEntityDTO>(BUNDLE_KEY)?.toUserEntity()?.let {
            viewModel.provideUserData(it)
        }
        viewModel.errorLiveData.observe(this) { showError(it) }
        viewModel.loadingLiveData.observe(this) { showLoading(it) }
        viewModel.userLiveData.observe(this) { showUser(it) }
        viewModel.userDetailsLiveData.observe(this) { showUserDetails(it) }
        viewModel.userRepoListLiveData.observe(this) { showRepoList(it) }
    }

    private fun getViewModel(): DetailsContract.ViewModel {
        return lastCustomNonConfigurationInstance as? DetailsContract.ViewModel
            ?: UserDetailsViewModel(app.userDetailsRepo)
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewRepositoryList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewRepositoryList.adapter = adapter
    }

    private fun showUser(userEntity: UserEntity) {
        binding.userLogin.text = userEntity.login
        binding.userId.text = userEntity.id.toString()
        binding.userAvatar.load(userEntity.avatarUrl)
    }

    private fun showUserDetails(details: UserDetailsEntity) {
        binding.userName.text = details.name
    }

    private fun showRepoList(repos: List<UserRepoEntity>) {
        adapter.setData(repos)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.recyclerViewRepositoryList.isVisible = !isLoading
    }

    private fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }
}