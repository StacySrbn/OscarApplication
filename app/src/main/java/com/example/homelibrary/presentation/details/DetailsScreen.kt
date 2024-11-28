package com.example.homelibrary.presentation.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.presentation.core.dashboard.BannersState
import com.example.homelibrary.presentation.core.dashboard.MovieListState

@Composable
fun DetailsScreen(
    movieListState: MovieListState,
    bannersState: BannersState,
    navController: NavHostController,
    upcomingMovieList: LazyPagingItems<Movie>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
){

    

}