package com.example.homelibrary.presentation.core.dashboard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homelibrary.R
import com.example.homelibrary.util.Dimens.BigPadding
import com.example.homelibrary.util.Dimens.SmallPadding
import com.example.homelibrary.presentation.common.MovieRowSlider
import com.example.homelibrary.presentation.core.dashboard.components.NewsCarousel
import com.example.homelibrary.presentation.core.dashboard.components.TopBar


@Composable
fun DashboardScreen(
    movieListState: MovieListState,
    oneEvent: (MovieListUiEvent) -> Unit,
    newsCards: List<String>,
    navController: NavHostController
){

    val popularMovieList = movieListState.popularMovieList
    val upcomingMovieList = movieListState.upcomingMovieList

    Column (
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.milk_white))
    ){
        TopBar()
        Spacer(modifier = Modifier.height(SmallPadding))


        NewsCarousel(cards = newsCards)
        Spacer(modifier = Modifier.height(27.dp))

        if (movieListState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (movieListState.errorMessage != null) {
            Toast.makeText(LocalContext.current, "Error: ${movieListState.errorMessage}", Toast.LENGTH_SHORT).show()
        } else {

            if (popularMovieList.isNotEmpty()) {
                val labelPopular = stringResource(id = R.string.popular_label)
                MovieRowSlider(label = labelPopular, movieList = popularMovieList, navController)
                Spacer(modifier = Modifier.height(BigPadding))
            }


            if (upcomingMovieList.isNotEmpty()) {
                val labelUpcoming = stringResource(id = R.string.upcoming_label)
                MovieRowSlider(label = labelUpcoming, movieList = upcomingMovieList, navController)
                Spacer(modifier = Modifier.height(BigPadding))
            }
        }

    }
}