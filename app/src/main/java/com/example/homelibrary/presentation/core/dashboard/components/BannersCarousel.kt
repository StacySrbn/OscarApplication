package com.example.homelibrary.presentation.core.dashboard.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.homelibrary.R
import com.example.homelibrary.domain.model.Banner
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.presentation.common.DotIndicator
import com.example.homelibrary.presentation.common.ShimmerBannerItem
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.util.Dimens.SmallPadding


@Composable
fun BannersCarousel(
    banners: List<Banner>,
    isLoading: Boolean,
    navHostController: NavHostController
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { banners.size }
    )

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(start = MediumPadding),
                text = stringResource(R.string.watch_now_label),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                onTextLayout = {}
            )
        }

        Spacer(modifier = Modifier.height(SmallPadding))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            key = { banners[it].id }
        ) { index ->

            ShimmerBannerItem(
                isLoading = isLoading,
                contentAfterLoading = {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .padding(horizontal = MediumPadding)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray)
                            .clickable {
                                navHostController.navigate(Screen.DetailsScreen.withArgs("${banners[index].id}"))
                            }
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = rememberAsyncImagePainter(model = banners[index].imageUrl),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }
                }
            )

        }
        Spacer(modifier = Modifier.height(SmallPadding))
        DotIndicator(
            modifier = Modifier.width(52.dp),
            itemsSize = banners.size,
            selectedItem = pagerState.currentPage
        )
    }

}

