package com.example.hw_02_m6_rickmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_02_m6_rickmorty.data.Repository
import com.example.hw_02_m6_rickmorty.data.model.Character
import com.example.hw_02_m6_rickmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getCharacters() = repository.getCharacters()

}
