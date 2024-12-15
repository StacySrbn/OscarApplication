package com.example.homelibrary.presentation.details_actor

import com.example.homelibrary.domain.model.Actor
import com.example.homelibrary.domain.model.Movie

data class ActorDetailsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val actor: Actor? = null
)
