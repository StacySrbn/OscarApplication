package com.example.homelibrary.data.local_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviedb.db")
data class MovieEntity(
    @PrimaryKey
    val id: Int,

    val category: String,

    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)