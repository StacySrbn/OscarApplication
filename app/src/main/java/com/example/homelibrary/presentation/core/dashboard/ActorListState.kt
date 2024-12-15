package com.example.homelibrary.presentation.core.dashboard

import androidx.paging.PagingData
import com.example.homelibrary.domain.model.Actor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ActorListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val actorList: Flow<PagingData<Actor>> = emptyFlow(),
)
