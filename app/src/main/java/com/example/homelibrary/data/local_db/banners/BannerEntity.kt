package com.example.homelibrary.data.local_db.banners

import androidx.room.*

@Entity(tableName = "banners")
data class BannerEntity (
    @PrimaryKey val id: Int,
    val imageUrl: String
)