package com.example.hw_02_m6_rickmorty.api

import com.example.hw_02_m6_rickmorty.data.model.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<BaseResponse<Any?>>

    @GET("character/")
    suspend fun getNextPage(@Query("page") page: Int): Response<BaseResponse<Character>>

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): Response<Character>
}
