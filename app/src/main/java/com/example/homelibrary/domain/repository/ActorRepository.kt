package com.example.homelibrary.domain.repository

import androidx.paging.PagingData
import com.example.homelibrary.domain.model.Actor
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.flow.Flow

interface ActorRepository {

    suspend fun getActorList(): Flow<PagingData<Actor>>

    suspend fun getActor(id: Int) : Flow<Resource<Actor>>

}