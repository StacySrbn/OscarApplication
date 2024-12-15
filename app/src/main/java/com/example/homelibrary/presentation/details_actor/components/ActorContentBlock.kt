package com.example.homelibrary.presentation.details_actor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.*
import coil.request.ImageRequest
import coil.size.Size
import com.example.homelibrary.R
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.presentation.common.RatingBar
import com.example.homelibrary.presentation.details_actor.ActorDetailsState
import com.example.homelibrary.util.Dimens

@Composable
fun ActorContentBlock(
    actorDetailsState: ActorDetailsState
){

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + actorDetailsState.actor?.profilePath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    actorDetailsState.actor?.let { actor ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageState is AsyncImagePainter.State.Loading){
                ShimmerActorImage(
                    isLoading = true,
                    contentAfterLoading = { }
                )
            }
            if (imageState is AsyncImagePainter.State.Success){
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    painter = imageState.painter,
                    contentDescription = actor.name,
                    contentScale = ContentScale.Crop
                )
            }
            if (imageState is AsyncImagePainter.State.Error){
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription = null,
                    )
                }
            }

            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))

            Text(
                text = actor.knownForDepartment,
                color = colorResource(id = R.color.graphite),
                fontWeight = FontWeight.W600,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = actor.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                RatingBar(
                    starsModifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterVertically),
                    rating = actor.popularity/2,
                    starsColor = colorResource(id = R.color.hot_orange)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterVertically),
                    text = actor.popularity.toString().take(3),
                    color = colorResource(id = R.color.graphite),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    maxLines = 1
                )
            }
        }
    }
}