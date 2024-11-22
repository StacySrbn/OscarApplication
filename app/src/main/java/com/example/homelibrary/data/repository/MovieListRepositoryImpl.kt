package com.example.homelibrary.data.repository


import androidx.paging.*
import com.example.homelibrary.data.local_db.MovieDatabase
import com.example.homelibrary.data.local_db.MovieRemoteMediator
import com.example.homelibrary.data.mappers.toMovie
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.domain.model.Movie
import com.example.homelibrary.domain.repository.MovieListRepository
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : MovieListRepository {
    override suspend fun getMovieList(category: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            remoteMediator = MovieRemoteMediator(movieDatabase, movieApi, category),
            pagingSourceFactory = { movieDatabase.movieDao.pagingSourceMovieListByCategory(category) }
        ).flow.map { pagingData ->
            pagingData.map { it.toMovie(category) }
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