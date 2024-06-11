package com.example.hw_02_m6_rickmorty.ui

import CharactersAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_02_m6_rickmorty.databinding.ActivityCharactersBinding
import com.example.hw_02_m6_rickmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val adapter = CharactersAdapter()
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.rvRick.layoutManager = LinearLayoutManager(this)
        binding.rvRick.adapter = adapter
    }

    private fun observeData() {
        viewModel.getCharacters().observe(this){
            adapter.submitData(this.lifecycle, it)
        }
    }
}
