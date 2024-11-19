package com.example.homelibrary.di

import com.example.homelibrary.data.repository.AuthRepositoryImpl
import com.example.homelibrary.data.repository.MovieListRepositoryImpl
import com.example.homelibrary.domain.repository.AuthRepository
import com.example.homelibrary.domain.repository.MovieListRepository
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        movieListRepositoryImpl: MovieListRepositoryImpl
    ) : MovieListRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository


}