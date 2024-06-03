package com.example.hw_02_m6_rickmorty.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeModel(
    @SerializedName("info")
    val info: InfoX,
    @SerializedName("results")
    val resultsEpisode: List<Result>
)