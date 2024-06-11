package com.example.hw_02_m6_rickmorty.ui.character_detail

import androidx.lifecycle.ViewModel
import com.example.hw_02_m6_rickmorty.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {
}