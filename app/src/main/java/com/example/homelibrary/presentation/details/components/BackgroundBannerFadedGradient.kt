package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.homelibrary.R
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.presentation.details.DetailsState
import com.example.homelibrary.util.Dimens
import com.example.homelibrary.util.Dimens.PosterSize

@Composable
fun BackgroundBannerFadedGradient(
    detailsState: DetailsState
){
    val bannerImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.backdropPath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(PosterSize),
        contentAlignment = Alignment.Center
    ) {
        if (bannerImageState is AsyncImagePainter.State.Error) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PosterSize)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = detailsState.movie?.title
                )
            }
        }

        if (bannerImageState is AsyncImagePainter.State.Loading) {
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }

        }

        if (bannerImageState is AsyncImagePainter.State.Success) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PosterSize),
                painter = bannerImageState.painter,
                contentDescription = detailsState.movie?.title,
                contentScale = ContentScale.Crop
            )
        }
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