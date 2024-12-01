package com.example.homelibrary.domain.repository

interface DetailsRepository {
    suspend fun getGenreMap(): Map<Int, String>

    suspend fun getMovieTrailerId(movieId: Int): String?
}