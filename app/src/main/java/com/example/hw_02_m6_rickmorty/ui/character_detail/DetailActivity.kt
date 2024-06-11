package com.example.hw_02_m6_rickmorty.ui.character_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_02_m6_rickmorty.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}