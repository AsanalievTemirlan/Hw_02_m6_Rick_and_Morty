package com.example.hw_02_m6_rickmorty.data.model

import com.google.gson.annotations.SerializedName


data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)