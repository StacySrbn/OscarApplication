package com.example.homelibrary.data.remote.response.search

data class SearchListDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)