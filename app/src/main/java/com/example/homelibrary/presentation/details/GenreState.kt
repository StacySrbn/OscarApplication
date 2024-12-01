package com.example.homelibrary.presentation.details

data class GenreState(
    val isLoading: Boolean = false,
    val genres: Map<Int, String>? = null,
    val error: String? = null
)
