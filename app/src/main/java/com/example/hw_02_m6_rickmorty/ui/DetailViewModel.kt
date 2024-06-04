package com.example.hw_02_m6_rickmorty.ui

import androidx.lifecycle.ViewModel
import com.example.hw_02_m6_rickmorty.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {
}