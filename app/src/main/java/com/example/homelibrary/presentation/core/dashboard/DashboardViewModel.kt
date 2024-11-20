package com.example.homelibrary.presentation.core.dashboard

import androidx.lifecycle.*
import androidx.paging.*
import com.example.homelibrary.domain.repository.MovieListRepository
import com.example.homelibrary.domain.use_cases.dashboard.GetAudiobookListUseCase
import com.example.homelibrary.util.Constants.POPULAR_CATEGORY
import com.example.homelibrary.util.Constants.UPCOMING_CATEGORY
import com.example.homelibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getAudiobookListUseCase: GetAudiobookListUseCase
): ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()



    init {
        getMovieList(POPULAR_CATEGORY)
        getMovieList(UPCOMING_CATEGORY)
    }

    private fun getMovieList(category: String){
        viewModelScope.launch {

            try {
                _movieListState.update { it.copy(isLoading = true, errorMessage = null) }

                val moviesFlow = getAudiobookListUseCase(category).cachedIn(viewModelScope)

                _movieListState.update { state ->

                    when (category) {
                        POPULAR_CATEGORY -> state.copy(popularMovieList = moviesFlow)
                        UPCOMING_CATEGORY -> state.copy(upcomingMovieList = moviesFlow)
                        else -> state
                    }
                }

            } catch (e: Exception) {
                _movieListState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "An unexpected error occurred."
                    )
                }
            } finally {
                _movieListState.update { it.copy(isLoading = false) }
            }

        }
    }
}