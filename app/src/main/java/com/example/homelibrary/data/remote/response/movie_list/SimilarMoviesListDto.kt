package com.example.homelibrary.data.remote.response.movie_list

data class SimilarMoviesListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)