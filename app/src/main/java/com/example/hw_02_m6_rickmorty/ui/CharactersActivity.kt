package com.example.hw_02_m6_rickmorty.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.hw_02_m6_rickmorty.R
import com.example.hw_02_m6_rickmorty.databinding.ActivityCharactersBinding

class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment)
        binding.button.setOnClickListener {
            navController.navigate(R.id.detailActivity)
        }
    }
}