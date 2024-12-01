package com.example.homelibrary.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.example.homelibrary.R
import com.example.homelibrary.presentation.details.components.*
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.util.Dimens.PosterSize
import com.example.homelibrary.util.Dimens.SmallPadding

@Composable
fun DetailsScreen(
    detailsState: DetailsState,
    genreState: GenreState,
    trailerState: TrailerState,
    navController: NavHostController,
    //similarMovieList: LazyPagingItems<Movie>,
    //isRefreshing: Boolean,
    //onRefresh: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.milk_white))
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(PosterSize)
        ){
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
        //BODY
        Spacer(modifier = Modifier.height(SmallPadding))

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

        TrailerSection(trailerState)

        Spacer(modifier = Modifier.height(SmallPadding))

        ActorsSection()

        Spacer(modifier = Modifier.height(SmallPadding))

        ReviewsSection()

        Spacer(modifier = Modifier.height(SmallPadding))

        SimilarMoviesSection()

    }
}