package com.example.homelibrary.presentation.core.dashboard

import com.example.homelibrary.domain.model.Banner

data class BannersState (
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    val bannersList: List<Banner> = emptyList()
)