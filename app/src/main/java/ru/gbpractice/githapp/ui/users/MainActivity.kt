package ru.gbpractice.githapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.gbpractice.githapp.App.Companion.BUNDLE_KEY
import ru.gbpractice.githapp.app
import ru.gbpractice.githapp.data.retrofit.entitiesDTO.UserEntityDTO
import ru.gbpractice.githapp.databinding.ActivityMainBinding
import ru.gbpractice.githapp.domain.entities.UserEntity
import ru.gbpractice.githapp.ui.details.UserDetailsActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private lateinit var viewModel: UsersContract.ViewModel

    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()

    }

    private fun initViewModel() {
        viewModel = getViewModel()

        viewModelDisposable.addAll(
            binding.buttonRefreshUserList.buttonClickObservable().subscribe {viewModel.onRefreshUserList()},
            viewModel.usersLiveData.subscribe { showUsersList(it) },
            viewModel.loadingLiveData.subscribe { showLoading(it) },
            viewModel.errorLiveData.subscribe { showError(it) },
            viewModel.showUserDetailsLiveData.subscribe { showUserDetails(it) }
        )
    }

    private fun getViewModel(): UsersContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UsersContract.ViewModel
            ?: UsersViewModel(app.appComponent.getUserListRepo())
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.ViewModel {
        return viewModel
    }

    private fun initViews() {
        showLoading(false)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.usersListRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnUserClickListener(object : UsersAdapter.OnUserClickListener {
            override fun onUserItemClick(userEntity: UserEntity) {
                viewModel.onSelectUser(userEntity)
            }
        })
        binding.usersListRecyclerView.adapter = adapter
    }

    private fun showUsersList(users: List<UserEntity>) {
        adapter.setData(users)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.usersListRecyclerView.isVisible = !isLoading
    }

    private fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }

    private fun showUserDetails(userEntity: UserEntity) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(BUNDLE_KEY, UserEntityDTO.fromUserEntity(userEntity))
        startActivity(intent)
    }
}