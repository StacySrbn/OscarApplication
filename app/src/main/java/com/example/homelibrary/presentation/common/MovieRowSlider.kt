package com.example.homelibrary.presentation.common

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.presentation.core.dashboard.components.HeaderRowSlider
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.util.Dimens.SmallPadding

@Composable
fun MovieRowSlider(
    label: String,
    movieList: LazyPagingItems<Movie>,
    navHostController: NavHostController
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        HeaderRowSlider(label)
        Spacer(modifier = Modifier.height(SmallPadding))
        LazyRow {
            items(movieList.itemCount) { index ->
                val movie = movieList[index]
                val lastItemEndPadding = if (index == movieList.itemCount - 1) MediumPadding else 0.dp
                movie?.let { MovieCardViewHolder(movie, lastItemEndPadding, navHostController)}
                if (movie != null) {
                    Log.d("MY_LOG", "Movie of index $index had ID: ${movie.id}")
                }
            }
        }
    }
}