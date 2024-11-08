package com.example.homelibrary.domain.model

data class AudiobookResponse(
    val authors: List<Author>,
    val available_markets: List<String>,
    val copyrights: List<Copyright>,
    val description: String,
    val html_description: String,
    val edition: String,
    val explicit: Boolean,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val images: List<ImageObject>,
    val languages: List<String>,
    val media_type: String,
    val name: String,
    val narrators: List<Narrator>,
    val publisher: String,
    val type: String,
    val uri: String,
    val total_chapters: Int,
    val chapters: Chapters
)

data class Author(
    val name: String
)

data class Copyright(
    val text: String,
    val type: String
)

data class ExternalUrls(
    val spotify: String
)

data class ImageObject(
    val url: String,
    val height: Int?,
    val width: Int?
)

data class Narrator(
    val name: String
)

data class Chapters(
    val href: String,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int,
    val items: List<Chapter>
)

data class Chapter(
    val audio_preview_url: String,
    val available_markets: List<String>,
    val chapter_number: Int,
    val description: String,
    val html_description: String,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val images: List<ImageObject>,
    val is_playable: Boolean,
    val languages: List<String>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val resume_point: ResumePoint,
    val type: String,
    val uri: String,
    val restrictions: Restrictions
)

data class ResumePoint(
    val fully_played: Boolean,
    val resume_position_ms: Int
)

data class Restrictions(
    val reason: String
)