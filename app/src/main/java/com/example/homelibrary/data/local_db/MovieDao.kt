package com.example.homelibrary.data.local_db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM `moviedb.db` WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    //@Query("SELECT * FROM `moviedb.db` WHERE category = :category")
    //suspend fun getMovieListByCategory(category: String): List<MovieEntity>

    @Query("SELECT * FROM `moviedb.db` WHERE category = :category")
    fun pagingSourceMovieListByCategory(category: String): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM `moviedb.db` WHERE category = :category")
    suspend fun clearMoviesByCategory(category: String)

}