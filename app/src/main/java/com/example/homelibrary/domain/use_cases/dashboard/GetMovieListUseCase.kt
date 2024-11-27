package com.example.homelibrary.domain.use_cases.dashboard

import androidx.paging.PagingData
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.domain.repository.MovieListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val movieListRepository: MovieListRepository
){
    suspend operator fun invoke(category: String): Flow<PagingData<Movie>> {
        return movieListRepository.getMovieList(category)
    }
}