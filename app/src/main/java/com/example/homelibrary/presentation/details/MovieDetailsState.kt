package com.example.homelibrary.presentation.details

import com.example.homelibrary.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val movie: Movie? = null
)