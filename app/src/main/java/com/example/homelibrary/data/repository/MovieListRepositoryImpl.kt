package com.example.homelibrary.data.repository

import com.example.homelibrary.data.local_db.MovieDatabase
import com.example.homelibrary.data.mappers.toMovie
import com.example.homelibrary.data.mappers.toMovieEntity
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.domain.repository.MovieListRepository
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)

            val shouldLoadLocalMovie = localMovieList.isEmpty() && !forceFetchFromRemote

            if(shouldLoadLocalMovie){
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
            } catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Network issue. Please check your internet connection and try again."))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                val errorMessage = when (e.code()) {
                    404 -> "Movies not found."
                    500 -> "Server error. Please try again later."
                    else -> "Unexpected server response. Please try again."
                }
                emit(Resource.Error(message = errorMessage))
                return@flow
            } catch (e: Exception){
                e.printStackTrace()
                emit(Resource.Error(message = "An unexpected error occurred. Please try again later."))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }

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