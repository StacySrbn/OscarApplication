package com.example.homelibrary.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.homelibrary.data.local_db.*
import com.example.homelibrary.domain.manager.LocalUserManager
import com.example.homelibrary.domain.use_cases.app_entry.*
import com.example.homelibrary.data.manager.LocalUserManagerImpl
import com.example.homelibrary.data.remote.MovieApi
import com.example.homelibrary.domain.repository.*
import com.example.homelibrary.domain.use_cases.auth.*
import com.example.homelibrary.domain.use_cases.dashboard.*
import com.example.homelibrary.presentation.signin.google.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideSignInClient(context: Context): SignInClient = Identity.getSignInClient(context)

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(context: Context): GoogleAuthUiClient {
        val oneTapClient = Identity.getSignInClient(context)
        return GoogleAuthUiClient(context, oneTapClient)
    }

    @Provides
    @Singleton
    fun provideReadAppEntryUseCase(
        localUserManager: LocalUserManager
    ): ReadAppEntry = ReadAppEntry(localUserManager)

    @Provides
    @Singleton
    fun provideSaveAppEntryUseCase(
        localUserManager: LocalUserManager
    ): SaveAppEntry = SaveAppEntry(localUserManager)


    @Provides
    @Singleton
    fun provideSignInUseCase(
        authRepository: AuthRepository
    ): SignInUseCase = SignInUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        authRepository: AuthRepository
    ): SignUpUseCase = SignUpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGetBannersUseCase(
        bannersRepository: BannersRepository
    ) : GetBannersUseCase = GetBannersUseCase(bannersRepository)

    @Provides
    @Singleton
    fun provideGetMovieListUseCase(
        movieListRepository: MovieListRepository
    ) : GetMovieListUseCase = GetMovieListUseCase(movieListRepository)

    @Provides
    @Singleton
    fun provideGetActorListUseCase(
        actorRepository: ActorRepository
    ) : GetActorListUseCase = GetActorListUseCase(actorRepository)



    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    @Provides
    @Singleton
    fun providesMovieApi() : MovieApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MovieApi.BASE_URL)
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGson() : Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesMovieDatabase(app: Application, gson: Gson): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "movie_db"
        ).addTypeConverter(Converters(gson))
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
    }

}