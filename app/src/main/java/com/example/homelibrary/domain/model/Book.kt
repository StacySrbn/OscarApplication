package com.example.homelibrary.domain.model

data class Book(
    val isbn: String,
    val title: String,
    val description: String,
    val rating: Double,
    val coverUrl: String,
    val categories: List<String>,
    val publicationDate: String,
    val publisher: String,
    val language: String,
    val pageCount: Int,
    val reviews: List<Review>,
    val reviewCount: Int,
    val author: Author,
    val availableFormats: List<String>,
    val ebook: Ebook,
    val audiobook: Audiobook
)

data class Review(
    val reviewer: String,
    val review: String,
    val rating: Double
)

data class Author(
    val id: String,
    val name: String,
    val profession: String,
    val bio: String,
    val photoUrl: String
)

data class Ebook(
    val fileUrl: String,
    val drmProtected: Boolean,
    val fontSizeOptions: List<Int>
)

data class Audiobook(
    val audioUrl: String,
    val drmProtected: Boolean,
    val narrator: String,
    val listeningTime: String,
    val playbackSpeedOptions: List<Double>
)
