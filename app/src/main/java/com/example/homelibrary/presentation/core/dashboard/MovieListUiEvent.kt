package com.example.homelibrary.presentation.core.dashboard

sealed interface MovieListUiEvent {
    data class Paginate(val category: String) : MovieListUiEvent
}