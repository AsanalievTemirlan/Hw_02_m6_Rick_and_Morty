package com.example.hw_02_m6_rickmorty.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hw_02_m6_rickmorty.api.ApiService
import com.example.hw_02_m6_rickmorty.data.model.Character
import com.example.hw_02_m6_rickmorty.data.model.EpisodeModel
import com.example.hw_02_m6_rickmorty.data.model.Result
import com.example.hw_02_m6_rickmorty.ui.Episode
import com.example.hw_02_m6_rickmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiService) {
    fun getCharacter(): LiveData<Resource<List<Character>>> {
        return liveData(Dispatchers.IO) {
            try {
                val response = api.getCharacters()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success(it.results))
                    }
                } else {
                    emit(Resource.Error(response.message()))
                }
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }

    fun getEpisodes(): LiveData<Resource<List<Result>>>{
        return liveData(Dispatchers.IO) {
            try {
                val response = api.getEpisodes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success(it.resultsEpisode))
                    }
                } else {
                    emit(Resource.Error(response.message()))
                }
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage?: "Unknown error"))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage?: "Unknown error"))
            }
        }
    }



}