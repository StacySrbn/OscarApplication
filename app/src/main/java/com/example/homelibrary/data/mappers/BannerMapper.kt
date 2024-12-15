package com.example.homelibrary.data.mappers

import com.example.homelibrary.data.local_db.banners.BannerEntity
import com.example.homelibrary.data.remote.response.banners.BannerDocument
import com.example.homelibrary.domain.model.Banner

fun BannerEntity.toBanner(): Banner {
    return Banner(
        id = id,
        imageUrl = imageUrl
    )
}

fun BannerDocument.toBannerEntity(): BannerEntity{
    return BannerEntity(
        id = id,
        imageUrl = imageUrl ?: ""
    )
}