package com.example.homelibrary.data.local_db.movie_list

import android.util.Log
import androidx.paging.*
import androidx.room.withTransaction
import com.example.homelibrary.data.local_db.MovieDatabase
import com.example.homelibrary.data.mappers.toMovieEntity
import com.example.homelibrary.data.remote.MovieApi
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieDb: MovieDatabase,
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
                }
            }
            val movieList = movieApi.getMoviesList(
                page = loadKey,
                category = category
            )

            Log.d("MovieRemoteMediator", "Total Results: $category , ${movieList.totalResults}, Total Pages: ${movieList.totalPages}")
            Log.d("MovieRemoteMediator", "${category} Page: ${movieList.page}, List Size: ${movieList.results.size}")

            movieDb.withTransaction {
                if (loadType == LoadType.REFRESH){
                    movieDb.movieDao.clearMoviesByCategory(category)
                }
                val movieEntities = movieList.results.map { movie->
                    movie.toMovieEntity(category)
                }
                movieDb.movieDao.upsertMovieList(movieEntities)
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