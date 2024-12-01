package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.homelibrary.R
import com.example.homelibrary.presentation.details.TrailerState
import com.example.homelibrary.util.Dimens

@Composable
fun TrailerSection(
    trailerState: TrailerState
){
    val trailerId = trailerState.youTubeTrailerId
    val isLoadingTrailer = trailerState.isLoading
    Column {
        Text(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding),
            text = stringResource(R.string.watch_the_trailer),
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        trailerId?.let {
            YouTubePlayer(
                youTubeTrailerId = it,
                lifecycleOwner = LocalLifecycleOwner.current,
                isLoading = isLoadingTrailer
            )
        }
        if (trailerId==null || trailerState.errorMessage!=null){
            Box (
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.MediumPadding)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Rounded.BrokenImage,
                    contentDescription = null
                )
            }
        }
    }
}