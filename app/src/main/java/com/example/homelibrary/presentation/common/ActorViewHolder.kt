package com.example.homelibrary.presentation.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.homelibrary.R
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.domain.model.Actor
import com.example.homelibrary.util.Dimens.AHundred
import com.example.homelibrary.util.Dimens.ExtraSmallPadding
import com.example.homelibrary.util.Dimens.MediumPadding

@Composable
fun ActorCardViewHolder(
    actor: Actor,
    lastItemEndPadding: Dp,
    navHostController: NavHostController
){
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(MovieApi.IMAGE_BASE_URL + actor.profilePath)
        .size(Size.ORIGINAL)
        .build()

    val painter = rememberAsyncImagePainter(model = imageRequest)

        Box(
            modifier = Modifier
                .size(AHundred)
                .padding(start = MediumPadding, end = lastItemEndPadding)
                .clickable {
//NAVIGATE TO ACTOR DETAIL SCREEN
                }
        ) {
            if (painter.state is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .size(102.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription = actor.name
                    )
                }
            }
            if (painter.state is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .size(102.dp)
                        .clip(CircleShape),
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = actor.name
                )
            }

            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            Text(
                text = actor.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = actor.knownForDepartment,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.graphite),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

}