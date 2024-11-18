package com.example.homelibrary.presentation.core.dashboard

import androidx.lifecycle.*
import com.example.homelibrary.domain.repository.MovieListRepository
import com.example.homelibrary.util.Constants.POPULAR_CATEGORY
import com.example.homelibrary.util.Constants.UPCOMING_CATEGORY
import com.example.homelibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
): ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getMovieList(false, POPULAR_CATEGORY)
        getMovieList(false, UPCOMING_CATEGORY)
    }

    fun onEvent(event: MovieListUiEvent) {
        when (event) {
            is MovieListUiEvent.Paginate -> {
                getMovieList(true, event.category)
            }
        }
    }

    private fun getMovieList(forceFetchFromRemote: Boolean, category: String){
        viewModelScope.launch {

            _movieListState.update {
                it.copy(isLoading = true)
            }

            val page = if (category == POPULAR_CATEGORY) {
                movieListState.value.popularMovieListPage
            } else {
                movieListState.value.upcomingMovieListPage
            }

            movieListRepository.getMovieList(
                forceFetchFromRemote,
                category,
                page
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message ?: "An unexpected error occurred.")
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let{ movieList ->
                            _movieListState.update {
                                if (category == POPULAR_CATEGORY){
                                    it.copy(
                                        popularMovieList = movieListState.value.popularMovieList + movieList.shuffled(),
                                        popularMovieListPage = page + 1
                                    )
                                } else {
                                    it.copy(
                                        upcomingMovieList = movieListState.value.upcomingMovieList + movieList.shuffled(),
                                        upcomingMovieListPage = page + 1
                                    )
                                }

                            }
                        }
                    }
                }

            }
        }
    }

}