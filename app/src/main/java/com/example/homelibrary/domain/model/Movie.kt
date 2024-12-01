package com.example.homelibrary.domain.model

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>, //TODO
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double, //TODO
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

    val category: String,
)