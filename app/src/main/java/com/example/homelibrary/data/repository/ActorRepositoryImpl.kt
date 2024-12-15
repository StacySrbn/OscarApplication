package com.example.homelibrary.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.homelibrary.data.local_db.MovieDatabase
import com.example.homelibrary.data.local_db.actors.ActorRemoteMediator
import com.example.homelibrary.data.mappers.toActor
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.domain.model.Actor
import com.example.homelibrary.domain.repository.ActorRepository
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ActorRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDb: MovieDatabase
): ActorRepository {

    override suspend fun getActorList(): Flow<PagingData<Actor>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            remoteMediator = ActorRemoteMediator(
                movieDb, movieApi
            ),
            pagingSourceFactory = {
                movieDb.actorDao.getAllActors()
            }
        ).flow.map { pagingData->
            pagingData.map { it.toActor() }
        }
    }

    override suspend fun getActor(id: Int): Flow<Resource<Actor>> {
        return flow {
            emit(Resource.Loading(true))

            val actorEntity = movieDb.actorDao.getActorWithKnownForById(id)
            emit(
                Resource.Success(
                    actorEntity.toActor()
                )
            )
            emit(Resource.Loading(false))
            emit(Resource.Error("Error: No actor found with ID $id"))
        }
    }
}