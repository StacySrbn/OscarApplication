package com.example.homelibrary.presentation.details

import com.example.homelibrary.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)