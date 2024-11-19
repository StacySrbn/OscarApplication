package com.example.homelibrary.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.presentation.core.dashboard.MovieListState
import com.example.homelibrary.presentation.core.dashboard.MovieListUiEvent
import com.example.homelibrary.presentation.core.dashboard.components.HeaderRowSlider
import com.example.homelibrary.util.Constants.POPULAR_CATEGORY
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.util.Dimens.SmallPadding

@Composable
fun MovieRowSlider(
    label: String,
    movieList: List<Movie>,
    navHostController: NavHostController,
    onEvent: (MovieListUiEvent) -> Unit,
    movieListState: MovieListState,
    category: String
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        HeaderRowSlider(label)
        Spacer(modifier = Modifier.height(SmallPadding))
        LazyRow {
            items(movieList.size) { index ->
                val movie = movieList[index]
                val lastItemEndPadding = if (index == movieList.size - 1) MediumPadding else 0.dp
                MovieCardViewHolder(movie, lastItemEndPadding, navHostController)

                if (index >= movieList.size - 1 && !movieListState.isLoading) {
                    onEvent(MovieListUiEvent.Paginate(category))
                }
            }
        }
    }
}