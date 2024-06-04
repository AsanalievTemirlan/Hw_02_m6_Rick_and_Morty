package com.example.hw_02_m6_rickmorty.data.model

import com.google.gson.annotations.SerializedName


data class BaseResponse<T>(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Character>
)