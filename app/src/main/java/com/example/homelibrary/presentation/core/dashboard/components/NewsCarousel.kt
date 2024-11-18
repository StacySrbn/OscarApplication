package com.example.homelibrary.presentation.core.dashboard.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.homelibrary.util.Dimens.ExtraSmallPadding
import com.example.homelibrary.util.Dimens.MediumPadding
import com.example.homelibrary.presentation.common.DotIndicator

@Composable
fun NewsCarousel(cards: List<String>) {

    var currentIndex: Int by remember { mutableIntStateOf(0) }

    Column {
        LazyRow {
            items(cards.size) { index ->
                val card = cards[index]
                val lastItemEndPadding = if (index == cards.size - 1) MediumPadding else 0.dp
                Box(
                    modifier = Modifier
                        .height(146.dp)
                        .width(327.dp)
                        .padding(start = MediumPadding, end = lastItemEndPadding)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray)
                        .clickable { currentIndex = index }
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = rememberAsyncImagePainter(card),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(ExtraSmallPadding))
        DotIndicator(
            modifier = Modifier.width(52.dp),
            itemsSize = cards.size,
            selectedItem = currentIndex
        )

    }
}
