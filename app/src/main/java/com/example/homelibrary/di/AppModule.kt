package com.example.homelibrary.di

import android.app.Application
import com.example.homelibrary.domain.manager.LocalUserManager
import com.example.homelibrary.domain.use_cases.app_entry.AppEntryUseCases
import com.example.homelibrary.domain.use_cases.app_entry.ReadAppEntry
import com.example.homelibrary.domain.use_cases.app_entry.SaveAppEntry
import com.example.homelibrary.manager.LocalUserManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUsesCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

}