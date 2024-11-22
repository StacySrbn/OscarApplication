package com.example.homelibrary.presentation.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.homelibrary.util.Dimens
import com.example.homelibrary.util.Dimens.MediumPadding

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFC5C3C3),
                Color(0xFF8F8D8D),
                Color(0xFFC5C3C3)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

@Composable
fun ShimmerMovieItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    lastItemEndPadding: Dp
){
    if (isLoading) {

        Box(
            modifier = Modifier
                .height(240.dp)
                .width(160.dp)
                .clip(RoundedCornerShape(12.dp))
                .padding(start = MediumPadding, end = lastItemEndPadding),
            contentAlignment = Alignment.Center
        ) {
            Column() {
                Box(
                    modifier = Modifier
                        .height(184.dp)
                        .width(130.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
                Box(
                    modifier = Modifier
                        .height(14.dp)
                        .width(128.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .shimmerEffect(),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .width(68.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .shimmerEffect(),
                    )
                    Box(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .height(14.dp)
                            .width(14.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .shimmerEffect(),
                    )
                }

            }
        }
    } else {
        contentAfterLoading()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieItemShimmerEffect() {
    ShimmerMovieItem(
        isLoading = true,
        lastItemEndPadding = 0.dp,
        contentAfterLoading = {
            Box(
                modifier = Modifier
                    .height(240.dp)
                    .width(160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Loaded Content", color = Color.Black)
            }
        }
    )
}


