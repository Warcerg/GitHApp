package ru.gbpractice.githapp.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gbpractice.githapp.domain.repos.UserListRepo
import ru.gbpractice.githapp.app
import ru.gbpractice.githapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepo: UserListRepo by lazy {app.userListRepo }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        showProgressBar(false)
        initRefreshButton()
        initRecyclerView()


    }

    private fun initRefreshButton() {
        binding.buttonRefreshUserList.setOnClickListener {
            showProgressBar(true)
            usersRepo.getUserList(
                onSuccess = {
                    showProgressBar(false)
                    adapter.setData(it)
                },
                onError = {
                    showProgressBar(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun initRecyclerView() {
        binding.usersListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersListRecyclerView.adapter = adapter
    }

    private fun showProgressBar(progressBarIsVissible: Boolean) {
        binding.progressBar.isVisible = progressBarIsVissible
        binding.usersListRecyclerView.isVisible = !progressBarIsVissible
    }
}