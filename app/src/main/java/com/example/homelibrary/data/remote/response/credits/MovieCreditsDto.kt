package com.example.homelibrary.data.remote.response.credits

data class MovieCreditsDto(
    val cast: List<CastDto>,
    val crew: List<CrewDto>,
    val id: Int
)