package com.example.hw_02_m6_rickmorty.ui

import CharactersAdapter
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_02_m6_rickmorty.databinding.ActivityCharactersBinding
import com.example.hw_02_m6_rickmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private var adapter: CharactersAdapter = CharactersAdapter()
    private val viewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter()

        binding.rvRick.layoutManager = LinearLayoutManager(this)
        binding.rvRick.adapter = adapter

    }

    private fun observeData() {
        viewModel.getCharacters().observe(this, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.submitList(resource.data)
                }
                is Resource.Error -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    // Показываем индикатор загрузки, если требуется
                }
            }
        })

    }
}


