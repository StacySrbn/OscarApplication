package com.example.homelibrary.data.repository

import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): DetailsRepository {

    private var genreMap: Map<Int, String>? = null
    override suspend fun getGenreMap(): Map<Int, String> {
        if (genreMap == null) {
            val response = movieApi.getGenres()
            genreMap = response.genres.associate { genre ->
                genre.id to genre.name
            }
        }
        return genreMap as Map<Int, String>
    }

    override suspend fun getMovieTrailerId(movieId: Int): String? {
        val response = movieApi.getMovieVideos(movieId)
        val trailer = response.results.firstOrNull{ trailer->
            trailer.site == "YouTube" && trailer.type == "Trailer"
        }
        return trailer?.key
    }
}