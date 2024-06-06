package com.example.hw_02_m6_rickmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_02_m6_rickmorty.data.Repository
import com.example.hw_02_m6_rickmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun getCharacters() = repository.getCharacters()

}
