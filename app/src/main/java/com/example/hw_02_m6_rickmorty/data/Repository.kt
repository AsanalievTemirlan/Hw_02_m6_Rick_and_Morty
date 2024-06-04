package com.example.hw_02_m6_rickmorty.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.hw_02_m6_rickmorty.api.ApiService
import com.example.hw_02_m6_rickmorty.data.model.Character
import com.example.hw_02_m6_rickmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiService) {
    private var charactersPage = 1
    private var isLoading = false
    private var isLastPage = false

    fun getCharacter(): LiveData<Resource<MutableList<Character>>> {
        return loadCharactersPage(charactersPage)
    }

    private fun loadCharactersPage(page: Int): LiveData<Resource<MutableList<Character>>> {
        return liveData(Dispatchers.IO) {
            isLoading = true
            try {
                val response = api.getNextPage(page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success(it.results))
                        isLastPage = it.results.isEmpty()
                    }
                } else {
                    emit(Resource.Error(response.message()))
                }
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
            isLoading = false
        }
    }

    fun nextPage(): LiveData<Resource<MutableList<Character>>> {
        if (!isLastPage && !isLoading) {
            charactersPage++
            return loadCharactersPage(charactersPage)
        }
        return MutableLiveData(Resource.Success(mutableListOf()))
    }
}
