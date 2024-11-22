package com.example.homelibrary.presentation.common

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import coil.compose.*
import coil.request.ImageRequest
import coil.size.Size
import com.example.homelibrary.R
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.presentation.navgraph.Screen
import com.example.homelibrary.util.Dimens

@Composable
fun MovieCardViewHolder(
    movie: Movie,
    lastItemEndPadding: Dp,
    navHostController: NavHostController
){
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(MovieApi.IMAGE_BASE_URL + movie.backdropPath)
        .size(Size.ORIGINAL)
        .build()

    val imageState = rememberAsyncImagePainter(model = imageRequest).state


        Box(
            modifier = Modifier
                .height(240.dp)
                .width(160.dp)
                .clip(RoundedCornerShape(12.dp))
                .padding(start = Dimens.MediumPadding, end = lastItemEndPadding)
                .clickable {
                    navHostController.navigate(Screen.DetailsScreen.withArgs("${movie.id}"))
                },
            contentAlignment = Alignment.Center
        ) {
            Column {

                if (imageState is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(130.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(60.dp),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = movie.title
                        )
                    }
                }
                if (imageState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .height(184.dp)
                            .width(130.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        painter = imageState.painter,
                        contentScale = ContentScale.Crop,
                        contentDescription = movie.title
                    )
                }


                Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = movie.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        starsModifier = Modifier.size(14.dp),
                        rating = movie.voteAverage / 2,
                        starsColor = colorResource(id = R.color.golden)
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = movie.voteAverage.toString().take(3),
                        color = colorResource(id = R.color.teal_main),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1
                    )
                }
            }
        }
}