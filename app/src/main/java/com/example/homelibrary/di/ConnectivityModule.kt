package com.example.homelibrary.di

import android.content.Context
import com.example.homelibrary.connectivity_observer.AndroidConnectivityObserver
import com.example.homelibrary.connectivity_observer.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Provides
    @Singleton
    fun provideAndroidConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return AndroidConnectivityObserver(context)
    }
}