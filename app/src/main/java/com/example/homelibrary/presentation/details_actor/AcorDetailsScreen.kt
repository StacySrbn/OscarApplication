package com.example.homelibrary.presentation.details_actor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.homelibrary.R
import com.example.homelibrary.presentation.details.components.BackgroundBannerFadedGradient
import com.example.homelibrary.presentation.details.components.MainDetailsBlock
import com.example.homelibrary.presentation.details.components.TopButtonsSection
import com.example.homelibrary.presentation.details_actor.components.ActorContentBlock
import com.example.homelibrary.presentation.details_actor.components.BackgroundGradient
import com.example.homelibrary.presentation.details_actor.components.NavigationTopRow
import com.example.homelibrary.util.Dimens
import com.example.homelibrary.util.Dimens.MediumPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActorDetailsScreen(
    navController: NavHostController,
    actorDetailsState: ActorDetailsState,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {

    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        state = state
    ) {

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.milk_white))
        ){

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.PosterSize)
                ) {
                    BackgroundGradient(actorDetailsState)

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MediumPadding),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        NavigationTopRow(navController)

                        ActorContentBlock(actorDetailsState)
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.SmallPadding))
            }

            item {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = MediumPadding),
                ){
                    Text(
                        text = stringResource(R.string.known_for),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(Dimens.SmallPadding))

                    //lazy grid (column) with known for
                }
            }
        }
    }
}