package ru.gbpractice.githapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gbpractice.githapp.app
import ru.gbpractice.githapp.databinding.ActivityMainBinding
import ru.gbpractice.githapp.domain.entities.UserEntity


class MainActivity : AppCompatActivity(), UsersContract.View {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private lateinit var presenter: UsersContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        presenter = retainPresenter()
        presenter.attach(this)

    }

    private fun retainPresenter(): UsersContract.Presenter {
        return lastCustomNonConfigurationInstance as? UsersContract.Presenter ?: app.userListPresenter
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initViews() {
        showLoading(false)
        initRefreshButton()
        initRecyclerView()


    }

    private fun initRefreshButton() {
        binding.buttonRefreshUserList.setOnClickListener {
            presenter.onRefreshUserList()
        }
    }

    private fun initRecyclerView() {
        binding.usersListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersListRecyclerView.adapter = adapter
    }


    override fun showUsersList(users: List<UserEntity>) {
        adapter.setData(users)
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.usersListRecyclerView.isVisible = !isLoading
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }
}