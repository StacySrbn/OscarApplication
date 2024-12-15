package com.example.homelibrary.data.local_db.actors

import androidx.paging.*
import androidx.room.withTransaction
import com.example.homelibrary.data.local_db.MovieDatabase
import com.example.homelibrary.data.mappers.toActorEntity
import com.example.homelibrary.data.mappers.toKnownForEntity
import com.example.homelibrary.data.remote.MovieApi
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class ActorRemoteMediator(
    private val movieDb: MovieDatabase,
    private val movieApi: MovieApi
): RemoteMediator<Int, ActorEntity>() {

    private var currentPage = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ActorEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->  return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> currentPage++
            }

            val actorList = movieApi.getPopularActors(
                page = loadKey
            )

            movieDb.withTransaction {
                if (loadType == LoadType.REFRESH){
                    movieDb.actorDao.clearAllActors()
                    movieDb.actorDao.clearAllKnownFor()
                }
                val actorEntities = actorList.results.map { actorDto ->
                    actorDto.toActorEntity()
                }

                val knownForEntities = actorList.results.flatMap { actorDto->
                    actorDto.known_for.map { knownForDto ->
                        knownForDto.toKnownForEntity(actorDto.id)
                    }
                }
                movieDb.actorDao.upsertActorList(actorEntities)
                movieDb.actorDao.upsertKnownFor(knownForEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = actorList.page >= actorList.total_pages
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