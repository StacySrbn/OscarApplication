package com.example.homelibrary.presentation.home_main.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.homelibrary.domain.model.Author
import com.example.homelibrary.R
import com.example.homelibrary.presentation.Dimens
import com.example.homelibrary.presentation.common.HeaderRowSlider

@Composable
fun AuthorsSlider(
    authors: List<Author>
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.MediumPadding)
    ){
        val label = stringResource(id = R.string.authors_label)
        HeaderRowSlider(label)
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        LazyRow {
            items(authors.size) { index ->
                val author = authors[index]
                val lastItemEndPadding = if (index == authors.size - 1) Dimens.MediumPadding else 0.dp
                AuthorCardViewHolder(author, lastItemEndPadding)
            }
        }
    }
}