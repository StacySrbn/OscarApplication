package com.example.homelibrary.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.navigation.NavHostController
import com.example.homelibrary.R
import com.example.homelibrary.presentation.details.components.*
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.util.Dimens.PosterSize
import com.example.homelibrary.util.Dimens.SmallPadding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    detailsState: DetailsState,
    genreState: GenreState,
    trailerState: TrailerState,
    navController: NavHostController,
    //similarMovieList: LazyPagingItems<Movie>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
){
    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        state = state
    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.milk_white))
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(PosterSize)
                ) {
                    BackgroundBannerFadedGradient(detailsState)

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MediumPadding),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TopButtonsSection(navController)

                        MainDetailsBlock(detailsState = detailsState)
                    }
                }
                Spacer(modifier = Modifier.height(SmallPadding))
            }
            //BODY
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MediumPadding)
                ) {
                    GenresChain(detailsState, genreState)

                    Spacer(modifier = Modifier.height(SmallPadding))

                    OverviewSection(detailsState)
                }
                Spacer(modifier = Modifier.height(SmallPadding))
            }
            item {
                TrailerSection(trailerState)

                Spacer(modifier = Modifier.height(SmallPadding))
            }
            item {
                ActorsSection()

                Spacer(modifier = Modifier.height(SmallPadding))
            }
            item {
                ReviewsSection()

                Spacer(modifier = Modifier.height(SmallPadding))
            }
            item {
                SimilarMoviesSection()

                Spacer(modifier = Modifier.height(SmallPadding))
            }






        }
    }
}