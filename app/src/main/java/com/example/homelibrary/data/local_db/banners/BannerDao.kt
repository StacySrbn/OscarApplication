package com.example.homelibrary.data.local_db.banners

import androidx.room.*

@Dao
interface BannerDao {

    @Upsert
    suspend fun upsertBanners(banners: List<BannerEntity>)

    @Query("SELECT * FROM banners")
    suspend fun getAllBanners(): List<BannerEntity>

    @Query("DELETE FROM banners")
    suspend fun clearBanners()

}