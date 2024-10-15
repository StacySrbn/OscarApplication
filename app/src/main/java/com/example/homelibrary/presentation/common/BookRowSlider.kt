package com.example.homelibrary.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import com.example.homelibrary.domain.model.Book
import com.example.homelibrary.presentation.Dimens.MediumPadding
import com.example.homelibrary.presentation.Dimens.SmallPadding

@Composable
fun BookRowSlider(
    label: String,
    books: List<Book>
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(MediumPadding)
    ){
        HeaderRowSlider(label)
        Spacer(modifier = Modifier.height(SmallPadding))
        LazyRow {
            items(books.size) { index ->
                val book = books[index]
                val lastItemEndPadding = if (index == books.size - 1) MediumPadding else 0.dp
                BookCardViewHolder(book, lastItemEndPadding)
            }
        }
    }
}