package com.example.homelibrary.domain.use_cases.details

import javax.inject.Inject

data class DetailsUseCases @Inject constructor(
    val getMovieById: GetMovieById,
    val getGenres: GetGenres,
    val getTrailerById: GetTrailerById
)