package com.example.homelibrary.domain.model

data class KnownFor(
    val id: Int,
    val actorId: Int,
    val backdropPath: String,
    val mediaType: String,
    val name: String,
    val title: String,
    val voteAverage: Double
)
