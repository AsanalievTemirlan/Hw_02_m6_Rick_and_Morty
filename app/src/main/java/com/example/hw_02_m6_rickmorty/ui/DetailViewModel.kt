package com.example.hw_02_m6_rickmorty.ui

import androidx.lifecycle.ViewModel
import com.example.hw_02_m6_rickmorty.data.Repository
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun getEpisodes() = repository.getEpisodes()
}