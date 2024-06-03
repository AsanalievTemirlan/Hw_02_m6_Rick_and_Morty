package com.example.hw_02_m6_rickmorty.api

import com.example.hw_02_m6_rickmorty.data.model.BaseResponse
import com.example.hw_02_m6_rickmorty.data.model.EpisodeModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): Response<BaseResponse>

    @GET("episode")
    suspend fun getEpisodes(): Response<EpisodeModel>
}