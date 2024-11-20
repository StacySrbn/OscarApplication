package com.example.homelibrary.data.local_db

import android.util.Log
import androidx.paging.*
import androidx.room.withTransaction
import com.example.homelibrary.data.mappers.toMovieEntity
import com.example.homelibrary.data.remote.MovieApi
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieDB: MovieDatabase,
    private val movieApi: MovieApi,
    private val category: String
): RemoteMediator<Int, MovieEntity>(){

    private var currentPage = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    currentPage++
                    /*val lastPage = state.pages.lastOrNull()?.nextKey
                    if (lastPage == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    lastPage + 1*/
                }
            }
            val movieList = movieApi.getMoviesList(
                page = loadKey,
                category = category
            )

            Log.d("MovieRemoteMediator", "Total Results: $category , ${movieList.totalResults}, Total Pages: ${movieList.totalPages}")
            Log.d("MovieRemoteMediator", "${category} Page: ${movieList.page}, List Size: ${movieList.results.size}")

            movieDB.withTransaction {
                if (loadType == LoadType.REFRESH){
                    movieDB.movieDao.clearMoviesByCategory(category)
                }
                val movieEntities = movieList.results.map { movie->
                    movie.toMovieEntity(category)
                }
                movieDB.movieDao.upsertMovieList(movieEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = movieList.page >= movieList.totalPages
            )

        } catch (e: IOException){
            MediatorResult.Error(e)
        } catch (e: HttpException){
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}