package com.example.hw_02_m6_rickmorty.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.hw_02_m6_rickmorty.api.ApiService
import com.example.hw_02_m6_rickmorty.data.model.Character
import com.example.hw_02_m6_rickmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiService) {

    fun getCharacters(page: Int = 1): LiveData<PagingData<Character>> {
        return Pager(
            pagingSourceFactory = {
                CharacterPagingSource(api)
            },
            config = PagingConfig(
                pageSize = 20
            )
        ).liveData
    }
}
