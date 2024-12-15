package com.example.homelibrary.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homelibrary.data.local_db.actors.ActorDao
import com.example.homelibrary.data.local_db.actors.ActorEntity
import com.example.homelibrary.data.local_db.actors.KnownForEntity
import com.example.homelibrary.data.local_db.banners.BannerDao
import com.example.homelibrary.data.local_db.banners.BannerEntity
import com.example.homelibrary.data.local_db.movie_list.MovieDao
import com.example.homelibrary.data.local_db.movie_list.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        BannerEntity::class,
        ActorEntity::class,
        KnownForEntity::class
               ],
    version = 3,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val bannerDao: BannerDao
    abstract val actorDao: ActorDao
}