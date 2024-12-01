package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.homelibrary.presentation.details.*
import com.example.homelibrary.util.Dimens
import com.example.homelibrary.R
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun GenresChain(
    detailsState: DetailsState,
    genreState: GenreState
){
    val isLoading = genreState.isLoading
    Column {
        Text(
            text = "Genres",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        detailsState.movie?.let { movie ->
            val genreNames = movie.genreIds.map { id->
                genreState.genres?.get(id) ?: "Unknown"

            }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp
            ) {
                genreNames.forEach {genreName->
                        ShimmerGenre(
                            isLoading = isLoading,
                            contentAfterLoading = {
                                Box(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .background(
                                            color = colorResource(id = R.color.milk_white),
                                        )
                                        .border(
                                            width = 2.dp,
                                            color = colorResource(id = R.color.teal_main),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = genreName,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W600,
                                        color = colorResource(id = R.color.teal_main)
                                    )
                                }
                            }
                        )

                }
            }
        }
    }
}