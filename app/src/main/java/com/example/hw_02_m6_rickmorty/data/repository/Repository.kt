package com.example.hw_02_m6_rickmorty.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.hw_02_m6_rickmorty.api.ApiService
import com.example.hw_02_m6_rickmorty.data.CharacterPagingSource
import com.example.hw_02_m6_rickmorty.data.model.Character

class Repository (private val api: ApiService) {

    fun getCharacters(page: Int = 1): LiveData<PagingData<Character>> {
        return Pager(
            pagingSourceFactory = {
                CharacterPagingSource(api)
            },
            config = PagingConfig(
                pageSize = 10
            )
        ).liveData
    }
}
