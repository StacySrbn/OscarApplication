package com.example.homelibrary.presentation.core.dashboard

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.homelibrary.util.Constants.POPULAR_CATEGORY
import com.example.homelibrary.util.Constants.UPCOMING_CATEGORY


@Composable
fun DashboardScreen(
    movieListState: MovieListState,
    oneEvent: (MovieListUiEvent) -> Unit,
    newsCards: List<String>,
    navController: NavHostController
){

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.milk_white))
    ){
        item{
            TopBar()
            Spacer(modifier = Modifier.height(SmallPadding))
        }

        item {
            NewsCarousel(cards = newsCards)
            Spacer(modifier = Modifier.height(27.dp))
        }

        item {
            if (movieListState.isLoading) {
                CircularProgressIndicator()
            } else if (movieListState.errorMessage != null) {
                Toast.makeText(LocalContext.current, "Error: ${movieListState.errorMessage}", Toast.LENGTH_SHORT).show()
            } else {

                if (movieListState.popularMovieList.isNotEmpty()) {
                    val labelPopular = stringResource(id = R.string.popular_label)
                    MovieRowSlider(label = labelPopular, movieList = movieListState.popularMovieList, navController, oneEvent, movieListState, POPULAR_CATEGORY)
                    Spacer(modifier = Modifier.height(BigPadding))
                } else println("Fetching movie list which is empty: ${movieListState.popularMovieList.isEmpty()}")
                Log.d("API_MY", "${movieListState.popularMovieList}")

                if (movieListState.upcomingMovieList.isNotEmpty()) {
                    val labelUpcoming = stringResource(id = R.string.upcoming_label)
                    MovieRowSlider(label = labelUpcoming, movieList = movieListState.upcomingMovieList, navController, oneEvent, movieListState, UPCOMING_CATEGORY)
                    Spacer(modifier = Modifier.height(BigPadding))
                }
            }
        }



    }
}