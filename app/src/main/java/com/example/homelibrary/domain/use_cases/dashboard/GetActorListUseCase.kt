package com.example.homelibrary.domain.use_cases.dashboard

import androidx.paging.PagingData
import com.example.homelibrary.domain.model.Actor
import com.example.homelibrary.domain.repository.ActorRepository
import com.example.homelibrary.domain.repository.MovieListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActorListUseCase @Inject constructor(
    private val actorListRepository: ActorRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Actor>>{
        return actorListRepository.getActorList()
    }
}