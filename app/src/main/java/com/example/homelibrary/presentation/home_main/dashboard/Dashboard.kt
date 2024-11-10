package com.example.homelibrary.presentation.home_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import com.example.homelibrary.domain.model.MyBook
import com.example.homelibrary.R
import com.example.homelibrary.domain.model.MyAuthor
import com.example.homelibrary.presentation.Dimens.BigPadding
import com.example.homelibrary.presentation.Dimens.SmallPadding
import com.example.homelibrary.presentation.common.BookRowSlider
import com.example.homelibrary.presentation.home_main.components.AuthorsSlider
import com.example.homelibrary.presentation.home_main.components.NewsCarousel
import com.example.homelibrary.presentation.home_main.components.TopBar


@Composable
fun HomeScreen(
    books: List<MyBook>,
    authors: List<MyAuthor>,
    newsCards: List<String>
){
    Column (
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.milk_white))
    ){
        TopBar()
        Spacer(modifier = Modifier.height(SmallPadding))

        NewsCarousel(cards = newsCards)
        Spacer(modifier = Modifier.height(27.dp))

        val label = stringResource(id = R.string.popular_label)
        BookRowSlider(label = label, books = books)
        Spacer(modifier = Modifier.height(BigPadding))

        AuthorsSlider(authors = authors)
        Spacer(modifier = Modifier.height(BigPadding))
    }
}