package com.example.homelibrary.data.repository


import android.util.Log
import com.example.homelibrary.data.local_db.MovieDatabase
import com.example.homelibrary.data.mappers.toMovie
import com.example.homelibrary.data.mappers.toMovieEntity
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.domain.repository.MovieListRepository
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.flow.*
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            Log.d("MovieListRepository", "Fetching movie list for category: $category, page: $page, forceFetchFromRemote: $forceFetchFromRemote")

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)
            Log.d("MovieListRepository", "Local movie list size: ${localMovieList.size}")

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                Log.d("MovieListRepository", "Emitting local movie list")
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMoviesList(category, page)
            } catch (e: IOException) {
                Log.e("MovieListRepository", "IOException: ${e.message}", e)
                emit(Resource.Error(message = "Network issue. Please check your internet connection and try again."))
                return@flow
            } catch (e: HttpException) {
                Log.e("MovieListRepository", "HttpException: ${e.message}", e)
                val errorMessage = when (e.code()) {
                    404 -> "Movies not found."
                    500 -> "Server error. Please try again later."
                    else -> "Unexpected server response. Please try again."
                }
                emit(Resource.Error(message = errorMessage))
                return@flow
            } catch (e: Exception) {
                Log.e("MovieListRepository", "Exception: ${e.message}", e)
                emit(Resource.Error(message = "An unexpected error occurred. Please try again later."))
                return@flow
            }

            Log.d("MovieListRepository", "Movie list from API size: ${movieListFromApi.results.size}")

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }
            Log.d("MovieListRepository", "Inserting ${movieEntities.size} movies to local database")

            movieDatabase.movieDao.upsertMovieList(movieEntities)
            emit(Resource.Success(
                movieEntities.map {
                    it.toMovie(category)
                }
            ))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))

            val movieEntity = movieDatabase.movieDao.getMovieById(id)
            if (movieEntity!=null){
                emit(Resource.Success(
                    movieEntity.toMovie(movieEntity.category)
                ))

                emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Error("Error: No movie found with ID $id"))
            emit(Resource.Loading(false))
        }
    }

}