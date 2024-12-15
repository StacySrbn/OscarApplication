package com.example.homelibrary.data.repository

import com.example.homelibrary.data.local_db.MovieDatabase
import com.example.homelibrary.data.mappers.toBanner
import com.example.homelibrary.data.mappers.toBannerEntity
import com.example.homelibrary.data.remote.response.banners.BannerDocument
import com.example.homelibrary.domain.model.Banner
import com.example.homelibrary.domain.repository.BannersRepository
import com.example.homelibrary.util.Resource
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.database.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BannersRepositoryImpl @Inject constructor(
    private val movieDatabase: MovieDatabase

): BannersRepository {

    private val bannersCollectionRef = FirebaseDatabase.getInstance().getReference("Banners")
    override suspend fun getBanners(): Flow<Resource<List<Banner>>> {

        return flow {
            val cachedBanners = movieDatabase.bannerDao.getAllBanners().map { it.toBanner() }
            if (cachedBanners.isNotEmpty()){
                emit(Resource.Success(cachedBanners))
            }
            println("banners in DB: $cachedBanners")

            try {
                val snapshot = bannersCollectionRef.get().await()
                val bannersList = if (snapshot.exists()){
                    snapshot.children.mapNotNull { retrievedData->
                        retrievedData.getValue(BannerDocument::class.java)
                    }.map { bannerDocument ->
                        bannerDocument.toBannerEntity()
                    }
                } else {
                    emptyList()
                }
                movieDatabase.bannerDao.upsertBanners(bannersList)

                val updatedBanners = movieDatabase.bannerDao.getAllBanners().map { bannerEntity->
                    bannerEntity.toBanner()
                }
                println("upserted banners in DB: $cachedBanners")
                emit(Resource.Success(updatedBanners))

            } catch (e: FirebaseNetworkException) {
                emit(Resource.Error("Network error: Unable to connect to the database. Please check your internet connection."))
            } catch (e: DatabaseException) {
                emit(Resource.Error("Database error: ${e.message ?: "An unexpected database error occurred."}"))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unexpected error occurred."))
            }
        }

    }
}