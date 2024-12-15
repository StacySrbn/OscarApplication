package com.example.homelibrary.presentation.details_actor.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.homelibrary.R
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.presentation.details_actor.ActorDetailsState
import com.example.homelibrary.util.Dimens.PosterSize
import com.example.homelibrary.util.getAverageColor

@Composable
fun BackgroundGradient(
    actorDetailsState: ActorDetailsState
){
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + actorDetailsState.actor?.profilePath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    if (imageState is AsyncImagePainter.State.Error) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(PosterSize)
                .background(MaterialTheme.colorScheme.secondaryContainer),
        )
    }
    if (imageState is AsyncImagePainter.State.Success) {
        dominantColor = getAverageColor(
            imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(PosterSize)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondaryContainer,
                            dominantColor
                        )
                    )
                )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, colorResource(id = R.color.milk_white)),
                )
            )
    )
}