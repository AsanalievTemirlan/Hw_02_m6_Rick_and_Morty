package com.example.hw_02_m6_rickmorty.ui.character

import CharactersAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.hw_02_m6_rickmorty.databinding.ActivityCharactersBinding


class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private lateinit var adapter: CharactersAdapter
    private val viewModel: CharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        setupObserve()
    }

    private fun initialize() {
        adapter = CharactersAdapter()
        binding.rvRick.adapter = adapter
    }

    private fun setupObserve() {
        viewModel.getCharacters().observe(this){
            adapter.submitData(this.lifecycle,it)
        }
    }
}
