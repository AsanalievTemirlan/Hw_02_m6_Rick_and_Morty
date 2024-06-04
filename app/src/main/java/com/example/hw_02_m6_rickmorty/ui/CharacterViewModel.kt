package com.example.hw_02_m6_rickmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw_02_m6_rickmorty.data.Repository
import com.example.hw_02_m6_rickmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var charactersPage = 1
    private val _characters = MutableLiveData<Resource<List<Character>>>()
    val characters: LiveData<Resource<List<Character>>> get() = _characters

    init {
        getCharacters(charactersPage)
    }

    fun getCharacters(page: Int) {
        _characters.postValue(Resource.Loading())
        repository.getCharacter(page).observeForever {
            _characters.postValue(it)
        }
    }

    fun nextPage() {
        charactersPage++
        getCharacters(charactersPage)
    }
}
