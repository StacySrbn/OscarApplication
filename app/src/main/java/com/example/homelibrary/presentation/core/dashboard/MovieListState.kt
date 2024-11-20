package com.example.homelibrary.presentation.core.dashboard

import androidx.paging.PagingData
import com.example.homelibrary.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    val popularMovieList: Flow<PagingData<Movie>> = emptyFlow(),
    val upcomingMovieList: Flow<PagingData<Movie>> = emptyFlow()
)
