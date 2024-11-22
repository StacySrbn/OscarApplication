package com.example.homelibrary.presentation.core.dashboard

import androidx.lifecycle.*
import androidx.paging.*
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.homelibrary.domain.repository.MovieListRepository
import com.example.homelibrary.domain.use_cases.dashboard.GetAudiobookListUseCase
import com.example.homelibrary.util.Constants.POPULAR_CATEGORY
import com.example.homelibrary.util.Constants.UPCOMING_CATEGORY
import com.example.homelibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
                _movieListState.update { state ->
                    when (category) {
                        POPULAR_CATEGORY -> state.copy(isLoadingPopular = true)
                        UPCOMING_CATEGORY -> state.copy(isLoadingUpcoming = true)
                        else -> state
                    }
                }

                val moviesFlow = getAudiobookListUseCase(category)
                    .cachedIn(viewModelScope)


                _movieListState.update { state ->
                    when (category) {
                        POPULAR_CATEGORY -> state.copy(popularMovieList = moviesFlow)
                        UPCOMING_CATEGORY -> state.copy(upcomingMovieList = moviesFlow)
                        else -> state
                    }
                }
                delay(50)

            } catch (e: Exception) {
                _movieListState.update { state ->
                    when (category) {
                        POPULAR_CATEGORY -> state.copy(isLoadingPopular = false, errorMessagePopular = e.message ?: "An unexpected error occurred.")
                        UPCOMING_CATEGORY -> state.copy(isLoadingPopular = false, errorMessageUpcoming = e.message ?: "An unexpected error occurred.")
                        else -> state
                    }
                }
            } finally {
                delay(3000)
                _movieListState.update { state ->
                    when (category) {
                        POPULAR_CATEGORY -> state.copy(isLoadingPopular = false)
                        UPCOMING_CATEGORY -> state.copy(isLoadingUpcoming = false)
                        else -> state
                    }
                }
            }

        }
    }
}