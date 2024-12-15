package com.example.homelibrary.data.local_db.actors

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface ActorDao {

    @Upsert
    suspend fun upsertActorList(actorList: List<ActorEntity>)
    @Upsert
    suspend fun upsertKnownFor(knownFor: List<KnownForEntity>)


    @Query("SELECT * FROM actor")
    fun getAllActors(): PagingSource<Int, ActorEntity>
    /*
    @Transaction
    @Query("SELECT * FROM actor")
    suspend fun getActorsWithKnownFor(): List<ActorKnownForCrossRef>
    */

    @Transaction
    @Query("SELECT * FROM actor WHERE id = :id")
    suspend fun getActorWithKnownForById(id: Int): ActorEntity


    @Query("DELETE FROM actor")
    suspend fun clearAllActors()
    @Query("DELETE FROM known_for")
    suspend fun clearAllKnownFor()

}