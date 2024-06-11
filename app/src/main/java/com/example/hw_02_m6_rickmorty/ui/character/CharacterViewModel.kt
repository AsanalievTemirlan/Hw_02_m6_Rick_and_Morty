package com.example.hw_02_m6_rickmorty.ui.character

import androidx.lifecycle.ViewModel
import com.example.hw_02_m6_rickmorty.data.repository.Repository

class CharacterViewModel (private val repository: Repository) : ViewModel() {
    fun getCharacters() = repository.getCharacters()
}
