package com.example.homelibrary.data.mappers

import com.example.homelibrary.data.remote.BannerDocument
import com.example.homelibrary.domain.model.Banner

fun BannerDocument.toBanner(): Banner {
    return Banner(
        id = id,
        imageUrl = imageUrl ?: ""
    )
}