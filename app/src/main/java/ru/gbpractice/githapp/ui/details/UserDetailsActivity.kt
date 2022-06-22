package ru.gbpractice.githapp.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gbpractice.githapp.app
import ru.gbpractice.githapp.databinding.ActivityUserDetailsBinding
import ru.gbpractice.githapp.domain.entities.UserDetailsEntity
import ru.gbpractice.githapp.domain.entities.UserRepoEntity
import ru.gbpractice.githapp.ui.users.UsersContract

class UserDetailsActivity: AppCompatActivity(), DetailsContract.View {

    private lateinit var binding: ActivityUserDetailsBinding
    private val adapter = UserReposAdapter()
    private lateinit var presenter: DetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initviews()

        presenter = retainPresenter()
        presenter.attach(this)
    }
    private fun retainPresenter(): DetailsContract.Presenter {
        return lastCustomNonConfigurationInstance as? DetailsContract.Presenter ?: app.userDetailsPresenter
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initviews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewRepositoryList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewRepositoryList.adapter = adapter
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