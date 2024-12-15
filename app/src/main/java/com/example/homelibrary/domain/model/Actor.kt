package com.example.homelibrary.domain.model

data class Actor(
    val id: Int,
    val knownFor: List<KnownFor>,
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    val profilePath: String
)

