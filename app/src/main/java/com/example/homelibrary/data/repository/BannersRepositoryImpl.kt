package com.example.homelibrary.data.repository

import com.example.homelibrary.data.mappers.toBanner
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
): BannersRepository {

    private val bannersCollectionRef = FirebaseDatabase.getInstance().getReference("Banners")
    override suspend fun getBanners(): Flow<Resource<List<Banner>>> {

        return flow {
            emit(Resource.Loading(true))
            println("Emitting: Resource.Loading(true)")

            try {
                val snapshot = bannersCollectionRef.get().await()
                val bannersList = if (snapshot.exists()){
                    snapshot.children.mapNotNull { retrievedData->
                        retrievedData.getValue(BannerDocument::class.java)
                    }.map { bannerDocument ->
                        bannerDocument.toBanner()
                    }
                } else {
                    throw NoSuchElementException("No banners found in the database")
                }
                emit(Resource.Success(bannersList))
                println("Emitting: Resource.Success(${bannersList.size} banners)")

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