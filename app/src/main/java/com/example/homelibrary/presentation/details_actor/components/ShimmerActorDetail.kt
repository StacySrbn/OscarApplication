package com.example.homelibrary.presentation.details_actor.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.homelibrary.presentation.common.shimmerEffect

@Composable
fun ShimmerActorImage(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit
){
    if (isLoading){
        Box (
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .shimmerEffect()
        )
    } else {
        contentAfterLoading()
    }
}