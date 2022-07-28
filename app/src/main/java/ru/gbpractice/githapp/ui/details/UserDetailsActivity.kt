package ru.gbpractice.githapp.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.gbpractice.dilib.DiTool.inject
import ru.gbpractice.githapp.App.Companion.BUNDLE_KEY
import ru.gbpractice.githapp.data.retrofit.entitiesDTO.UserEntityDTO
import ru.gbpractice.githapp.databinding.ActivityUserDetailsBinding
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity
import ru.gbpractice.githapp.domain.repos.UserDetailsRepo


class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    private val adapter = UserReposAdapter()
    private lateinit var viewModel: DetailsContract.ViewModel

    private val userDetailsRepo: UserDetailsRepo = inject()

    private val viewModelDisposable = CompositeDisposable()

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

        viewModelDisposable.addAll(
            viewModel.errorLiveData.subscribe { showError(it) },
            viewModel.loadingLiveData.subscribe { showLoading(it) },
            viewModel.userLiveData.subscribe { showUser(it) },
            viewModel.userDetailsLiveData.subscribe { showUserDetails(it) },
            viewModel.userRepoListLiveData.subscribe { showRepoList(it) }
        )

    }

    private fun getViewModel(): DetailsContract.ViewModel {
        return lastCustomNonConfigurationInstance as? DetailsContract.ViewModel
            ?: UserDetailsViewModel(userDetailsRepo)
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
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