package com.example.homelibrary.presentation.onboarding

import androidx.annotation.DrawableRes

data class Page(
    val title: String,
    val subtitle: String,
    @DrawableRes val image: Int
)

