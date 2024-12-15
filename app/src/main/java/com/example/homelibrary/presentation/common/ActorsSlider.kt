package com.example.homelibrary.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.example.homelibrary.R
import com.example.homelibrary.domain.model.Actor
import com.example.homelibrary.presentation.core.dashboard.components.HeaderRowSlider
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.util.Dimens.SmallPadding

@Composable
fun ActorsSlider(
    actorList: LazyPagingItems<Actor>,
    navHostController: NavHostController,
    isLoading: Boolean,
    label: String
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        HeaderRowSlider(label)
        Spacer(modifier = Modifier.height(SmallPadding))
        LazyRow {
            items(actorList.itemCount) { index ->
                val author = actorList[index]
                val lastItemEndPadding = if (index == actorList.itemCount - 1) MediumPadding else 0.dp
                author?.let {
                    ShimmerActorItem(
                        isLoading = isLoading,
                        contentAfterLoading = { ActorCardViewHolder(it, lastItemEndPadding, navHostController) },
                    )
                }
            }
        }
    }
}