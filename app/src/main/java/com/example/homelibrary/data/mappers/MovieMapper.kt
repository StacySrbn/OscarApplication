package com.example.homelibrary.data.mappers

import com.example.homelibrary.data.local_db.MovieEntity
import com.example.homelibrary.data.remote.response.movie_list.MovieDto
import com.example.homelibrary.domain.model.Movie


fun MovieDto.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        originalLanguage = originalLanguage ?: "",
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        voteAverage = voteAverage ?: 0.0,
        popularity = popularity ?: 0.0,
        voteCount = voteCount ?: 0,
        id = id ?: -1,
        originalTitle = originalTitle ?: "",
        video = video ?: false,
        genreIds = genreIds ?: emptyList(),

        category = category
    )
}

fun MovieEntity.toMovie(
    category: String
): Movie {
    return Movie(
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        id = id,
        adult = adult,
        originalTitle = originalTitle,
        genreIds = genreIds,

        category = category
    )
}
