package com.example.homelibrary.domain.repository

import com.example.homelibrary.domain.model.Banner
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.flow.Flow

interface BannersRepository {
    suspend fun getBanners() : Flow<Resource<List<Banner>>>
}