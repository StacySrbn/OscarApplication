package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.homelibrary.R
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.presentation.common.RatingBar
import com.example.homelibrary.presentation.details.DetailsState
import com.example.homelibrary.util.Dimens
import com.example.homelibrary.util.Dimens.ExtraSmallPadding
import com.example.homelibrary.util.Dimens.SmallPadding

@Composable
fun MainDetailsBlock(
    detailsState: DetailsState,
){
    val posterImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.posterPath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        detailsState.movie?.let { movie ->
            Column (
                modifier = Modifier
                    .height(240.dp)
                    .width(200.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = movie.title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding))
                Text(
                    text = "Released " + movie.releaseDate,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = colorResource(id = R.color.dark)
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        starsModifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically),
                        rating = movie.voteAverage / 2,
                        starsColor = colorResource(id = R.color.hot_orange)
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .align(Alignment.CenterVertically),
                        text = movie.voteAverage.toString().take(3),
                        color = colorResource(id = R.color.graphite),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(ExtraSmallPadding))
                Text(
                    text = movie.voteCount.toString() + " Votes",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.graphite)
                )
                if (movie.adult){
                    Spacer(modifier = Modifier.height(ExtraSmallPadding))
                    Box (
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = colorResource(id = R.color.hot_orange),
                                shape = MaterialTheme.shapes.small
                            ),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = stringResource(R.string._18_label),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W600,
                            color = colorResource(id = R.color.hot_orange)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(ExtraSmallPadding))
                    Box (
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                width = 2.dp,
                                color = colorResource(id = R.color.teal_medium),
                                shape = MaterialTheme.shapes.small
                            ),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = " 0+ ",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W600,
                            color = colorResource(id = R.color.teal_medium)
                        )
                    }
                }

            }
        }
        Box(
            modifier = Modifier
                .width(160.dp)
                .height(240.dp)
        ) {
            if (posterImageState is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription = detailsState.movie?.title
                    )
                }
            }

            if (posterImageState is AsyncImagePainter.State.Loading){
                ShimmerPoster(
                    isLoading = true,
                    contentAfterLoading = {}
                )
            }

            if (posterImageState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    painter = posterImageState.painter,
                    contentDescription = detailsState.movie?.title,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
fun LabelAdult(){
    Box (
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.hot_orange),
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            modifier = Modifier.padding(4.dp),
            text = " 18+ ",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.hot_orange)
        )
    }
}