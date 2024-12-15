package com.example.homelibrary.data.local_db

import android.util.Log
import androidx.room.*
import com.example.homelibrary.data.local_db.actors.KnownForEntity
import com.example.homelibrary.domain.model.KnownFor
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class Converters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromGenreIds(genreIds: List<Int>) : String {
        return try {
            gson.toJson(
                genreIds, object: TypeToken<List<Int>>() {}.type
            )
        } catch (e: JsonSyntaxException) {
            Log.e("Converters", "Failed to serialize genreIds: $genreIds", e)
            "[]"
        }
    }
    @TypeConverter
    fun toGenreIds(data: String) : List<Int> {
        return try {
            gson.fromJson(
                data, object: TypeToken<List<Int>>() {}.type
            ) ?: emptyList()
        } catch (e: JsonSyntaxException) {
            Log.e("Converters", "Failed to deserialize to String list from data: $data", e)
            emptyList()
        }
    }

    @TypeConverter
    fun fromKnownForList(knownFor: List<KnownForEntity>): String {
        return try {
            gson.toJson(
                knownFor, object: TypeToken<List<KnownForEntity>>() {}.type
            )
        } catch (e: JsonSyntaxException){
            Log.e("Converters", "Failed to serialize genreIds: $knownFor", e)
            "[]"
        }
    }

    @TypeConverter
    fun toKnownForList(data: String) : List<KnownForEntity> {
        return try {
            gson.fromJson(
                data, object : TypeToken<List<KnownForEntity>>() {}.type
            ) ?: emptyList()
        } catch (e: JsonSyntaxException) {
            Log.e("Converters", "Failed to deserialize to String list from data: $data", e)
            emptyList()
        }
    }

}