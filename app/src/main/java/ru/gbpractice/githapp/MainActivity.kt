package ru.gbpractice.githapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gbpractice.githapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepo: UserListRepo by lazy {app.userListRepo }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRefreshUserList.setOnClickListener {
            adapter.setData(usersRepo.getUserList())
        }
        binding.usersListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersListRecyclerView.adapter = adapter
    }
}