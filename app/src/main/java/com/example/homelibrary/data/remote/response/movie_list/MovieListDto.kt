package com.example.homelibrary.data.remote.response.movie_list

import com.google.gson.annotations.SerializedName

data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)