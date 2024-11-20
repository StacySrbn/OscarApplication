package com.example.homelibrary.domain.repository

import androidx.paging.PagingData
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(category: String): Flow<PagingData<Movie>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}