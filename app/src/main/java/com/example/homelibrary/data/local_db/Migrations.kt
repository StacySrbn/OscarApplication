package com.example.homelibrary.data.local_db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS banners (
                id INTEGER NOT NULL,
                imageUrl TEXT NOT NULL,
                PRIMARY KEY (id)
            )
            """.trimIndent()
        )    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {

    }
}

