package com.example.homelibrary.domain.use_cases.details

import androidx.paging.PagingData
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.domain.repository.MovieListRepository
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieById @Inject constructor(
    private val movieListRepository: MovieListRepository
){
    suspend operator fun invoke(id: Int): Flow<Resource<Movie>> {
        return movieListRepository.getMovie(id)
    }
}