package com.example.homelibrary.data.remote.response.actors

data class ActorsListDto(
    val page: Int,
    val results: List<ActorDto>,
    val total_pages: Int,
    val total_results: Int
)