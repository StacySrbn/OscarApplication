package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.homelibrary.presentation.common.shimmerEffect

@Composable
fun ShimmerPoster(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit
){
    if (isLoading){
        Box (
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray)
                .shimmerEffect()
        )
    } else {
        contentAfterLoading()
    }
}

@Composable
fun ShimmerGenre(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit
){
    if (isLoading){
        Box (
            modifier = Modifier
                .height(30.dp)
                .width(90.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray)
                .shimmerEffect()
        )
    } else {
        contentAfterLoading()
    }
}

@Composable
fun ShimmerYouTubePlayer(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit
){
    if (isLoading){
        Box (
            modifier = Modifier
                .height(270.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray)
                .shimmerEffect()
        )
    } else {
        contentAfterLoading()
    }
}