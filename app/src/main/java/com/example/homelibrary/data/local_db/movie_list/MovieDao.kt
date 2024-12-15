package com.example.homelibrary.data.local_db.movie_list

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.homelibrary.data.local_db.movie_list.MovieEntity

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    //@Query("SELECT * FROM movie WHERE category = :category")
    //suspend fun getMovieListByCategory(category: String): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE category = :category")
    fun pagingSourceMovieListByCategory(category: String): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movie WHERE category = :category")
    suspend fun clearMoviesByCategory(category: String)

}