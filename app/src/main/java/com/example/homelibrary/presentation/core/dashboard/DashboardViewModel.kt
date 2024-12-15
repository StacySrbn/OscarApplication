package com.example.homelibrary.presentation.core.dashboard

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import com.example.homelibrary.domain.use_cases.dashboard.GetActorListUseCase
import com.example.homelibrary.domain.use_cases.dashboard.GetBannersUseCase
import com.example.homelibrary.domain.use_cases.dashboard.GetMovieListUseCase
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
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getBannersUseCase: GetBannersUseCase,
    private val getActorListUseCase: GetActorListUseCase
): ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    private var _bannersState = MutableStateFlow(BannersState())
    val bannersState = _bannersState.asStateFlow()

    private var _actorListState = MutableStateFlow(ActorListState())
    val actorListState = _actorListState.asStateFlow()


    init {
        getBanners()
        getMovieList(POPULAR_CATEGORY)
        getMovieList(UPCOMING_CATEGORY)
    }

    fun refreshData(){
        viewModelScope.launch{
            getBanners()
            getMovieList(POPULAR_CATEGORY)
            getMovieList(UPCOMING_CATEGORY)
            getActorList()
        }

    }
    fun reloadBanners() {
        getBanners()
    }
    fun reloadMovieList(category: String) {
        getMovieList(category)
    }


    private fun getBanners(){
        viewModelScope.launch {
            getBannersUseCase().collect { result->
                when (result){
                    is Resource.Loading -> {_bannersState.update {
                        it.copy(isLoading = true)
                    }
                        Log.d("BannersState", "Loading state emitted: isLoading = true")
                    }
                    is Resource.Success -> {
                        _bannersState.update {
                            it.copy(
                                bannersList = result.data ?: emptyList()
                            )
                        }
                        Log.d("BannersState", "Success state emitted: bannersList = ${result.data}")

                        delay(500)
                        _bannersState.update {
                            it.copy(isLoading = false)
                        }
                        Log.d("BannersState", "Loading state emitted: isLoading = false")
                    }
                    is Resource.Error -> {_bannersState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message ?: "Failed to fetch banners."
                        )
                    }
                        Log.d("BannersState", "Error state emitted: errorMessage = ${result.message}")
                    }
                }
            }
        }
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

                val moviesFlow = getMovieListUseCase(category)
                    .cachedIn(viewModelScope)


                _movieListState.update { state ->
                    when (category) {
                        POPULAR_CATEGORY -> state.copy(popularMovieList = moviesFlow)
                        UPCOMING_CATEGORY -> state.copy(upcomingMovieList = moviesFlow)
                        else -> state
                    }
                }

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

    private fun getActorList(){
        viewModelScope.launch {
            try {
                _actorListState.update {
                    it.copy(isLoading = true)
                }
                val actorsFlow = getActorListUseCase().cachedIn(viewModelScope)
                _actorListState.update {
                    it.copy(actorList = actorsFlow)
                }
            } catch (e: Exception) {
                _movieListState.update {
                    it.copy(
                        isLoadingPopular = false,
                        errorMessagePopular = e.message ?: "An unexpected error occurred."
                    )
                }
            } finally {
                delay(3000)
                _movieListState.update {
                    it.copy(isLoadingUpcoming = false)
                }
            }
        }
    }
}