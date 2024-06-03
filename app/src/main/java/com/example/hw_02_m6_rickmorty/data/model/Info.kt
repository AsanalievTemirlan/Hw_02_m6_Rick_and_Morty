package com.example.hw_02_m6_rickmorty.data.model

import com.google.gson.annotations.SerializedName


data class Info(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("prev")
    val prev: Any
)