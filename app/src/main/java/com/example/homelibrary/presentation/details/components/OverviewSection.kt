package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.homelibrary.R
import com.example.homelibrary.presentation.details.DetailsState
import com.example.homelibrary.util.Dimens.SmallPadding

@Composable
fun OverviewSection(
    detailsState: DetailsState
){
    Column {
        Text(
            text = stringResource(R.string.overview_label),
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(SmallPadding))
        detailsState.movie?.let { movie ->
            Text(
                text = movie.overview,
                fontSize = 16.sp,
                color = colorResource(id = R.color.graphite)
            )
        }
    }
}