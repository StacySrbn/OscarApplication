package com.example.homelibrary.presentation.core.dashboard

import com.example.homelibrary.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,

    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList(),
)
