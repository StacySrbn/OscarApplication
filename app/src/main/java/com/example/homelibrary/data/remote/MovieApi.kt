package com.example.homelibrary.data.remote

import com.example.homelibrary.data.remote.response.credits.MovieCreditsDto
import com.example.homelibrary.data.remote.response.genres.GenreListDto
import com.example.homelibrary.data.remote.response.movie_list.MovieListDto
import com.example.homelibrary.data.remote.response.movie_list.SimilarMoviesListDto
import com.example.homelibrary.data.remote.response.trailers.TrailerListDto
import retrofit2.http.*

interface MovieApi {

    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = API_KEY
    ): GenreListDto

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): TrailerListDto

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1
    ): SimilarMoviesListDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieCreditsDto

    @GET("person/popular")
    suspend fun getPopularActors(
        @Query("api_key") apiKey: String
    )

    @GET("search/multi")
    suspend fun search(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    )

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "7998d7a068439baf583c5312ce997aa2"
    }
}