package ru.gbpractice.githapp.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gbpractice.githapp.databinding.ActivityUserDetailsBinding

class UserDetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}