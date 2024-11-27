package com.example.homelibrary.presentation.core.dashboard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.example.homelibrary.R
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.presentation.common.ErrorLoadingSign
import com.example.homelibrary.util.Dimens.SmallPadding
import com.example.homelibrary.presentation.common.MovieRowSlider
import com.example.homelibrary.presentation.core.dashboard.components.BannersCarousel
import com.example.homelibrary.presentation.core.dashboard.components.TopBar
import com.example.homelibrary.util.Dimens.BigPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun DashboardScreen(
    movieListState: MovieListState,
    bannersState: BannersState,
    navController: NavHostController,
    popularMovieList: LazyPagingItems<Movie>,
    upcomingMovieList: LazyPagingItems<Movie>,

    isRefreshing: Boolean,
    onRefresh: () -> Unit,

    onReloadBanners: () -> Unit,
    onReloadPopular: () -> Unit,
    onReloadUpcoming: () -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = isRefreshing
    )


    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.milk_white))
        ) {

            item {
                TopBar()
                Spacer(modifier = Modifier.height(SmallPadding))
            }

            item {
                if (bannersState.errorMessage != null) {
                    ErrorLoadingSign(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(261.dp)
                            .background(Color.Transparent)
                            .clickable {
                                onReloadBanners()
                            }
                    )
                } else {
                    val banners = bannersState.bannersList
                    val isLoading = bannersState.isLoading
                    BannersCarousel(
                        banners = banners,
                        isLoading = isLoading,
                        navHostController = navController
                    )
                }
                Spacer(modifier = Modifier.height(27.dp))
            }

            item {
                if (movieListState.errorMessagePopular != null) {
                    ErrorLoadingSign(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(274.dp)
                            .background(Color.Transparent)
                            .clickable {
                                onReloadPopular()
                            }
                    )
                } else {
                    val isLoadingPopular = movieListState.isLoadingPopular
                    val labelPopular = stringResource(id = R.string.popular_label)
                    MovieRowSlider(
                        label = labelPopular,
                        movieList = popularMovieList,
                        navController,
                        isLoadingPopular
                    )
                }
                Spacer(modifier = Modifier.height(BigPadding))
            }

            item {
                if (movieListState.errorMessageUpcoming != null) {
                    ErrorLoadingSign(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(274.dp)
                            .background(Color.Transparent)
                            .clickable {
                                onReloadUpcoming()
                            }
                    )
                } else {
                    val isLoadingUpcoming = movieListState.isLoadingUpcoming
                    val labelUpcoming = stringResource(id = R.string.upcoming_label)
                    MovieRowSlider(
                        label = labelUpcoming,
                        movieList = upcomingMovieList,
                        navController,
                        isLoadingUpcoming
                    )
                }
                Spacer(modifier = Modifier.height(BigPadding))
            }
        }

    }
}


    /*LaunchedEffect (bannersState.errorMessage){
        bannersState.errorMessage.let { message->
            Toast.makeText(context, "Error: $message", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(movieListState.errorMessagePopular) {
        movieListState.errorMessagePopular?.let { message ->
            Toast.makeText(context, "Error: $message", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(movieListState.errorMessageUpcoming) {
        movieListState.errorMessageUpcoming?.let { message ->
            Toast.makeText(context, "Error: $message", Toast.LENGTH_SHORT).show()
        }
    } */
