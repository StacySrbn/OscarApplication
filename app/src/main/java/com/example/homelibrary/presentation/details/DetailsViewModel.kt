package com.example.homelibrary.presentation.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.homelibrary.domain.use_cases.details.DetailsUseCases
import com.example.homelibrary.util.Resource
import com.google.gson.JsonParseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCases: DetailsUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val movieId = savedStateHandle.get<Int>("movieId")

    private var _detailsState = MutableStateFlow(DetailsState())
    val detailsState = _detailsState.asStateFlow()

    private val _genreState = MutableStateFlow(GenreState())
    val genreState = _genreState.asStateFlow()

    private val _trailerState = MutableStateFlow(TrailerState())
    val trailerState = _trailerState.asStateFlow()

    init {
        getMovie(movieId ?: -1)
        loadGenres()
        loadMovieTrailer(movieId ?: -1)
    }

    private fun getMovie(id: Int) {
        viewModelScope.launch {
            _detailsState.update {
                it.copy(isLoading = true)
            }

            detailsUseCases.getMovieById(id).collectLatest {result->
                when(result) {
                    is Resource.Error -> {
                        _detailsState.update {
                            it.copy(isLoading = false, errorMessage = result.message)
                        }
                    }
                    is Resource.Loading -> {
                        _detailsState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { movie ->
                            _detailsState.update {
                                it.copy(movie = movie)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadGenres(){
        viewModelScope.launch {
            _genreState.update {
                it.copy(isLoading = true)
            }
            try {
                val genres = detailsUseCases.getGenres()
                _genreState.update {
                    it.copy(
                        isLoading = false,
                        genres =  genres
                    )
                }
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is IOException -> "Network error. Please check your internet connection."
                    is HttpException -> "Server error. Please try again later. (${e.code()})"
                    is JsonParseException -> "Data error. Unable to parse server response."
                    else -> e.message ?: "An unknown error occurred."
                }
                _genreState.update {
                    it.copy(
                        isLoading = false,
                        error = errorMessage
                    )
                }
            }
        }
    }

    private fun loadMovieTrailer(movieId: Int){
        viewModelScope.launch {
            _trailerState.update {
                it.copy(isLoading = true)
            }
            try {
                val trailerId = detailsUseCases.getTrailerById(movieId)
                _trailerState.update {
                    it.copy(
                        isLoading = false,
                        youTubeTrailerId = trailerId
                    )
                }
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is IOException -> "Network error. Please check your internet connection."
                    is HttpException -> "Server error. Please try again later. (${e.code()})"
                    else -> e.message ?: "An unknown error occurred."
                }
                _trailerState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = errorMessage
                    )
                }
            }
        }
    }
}