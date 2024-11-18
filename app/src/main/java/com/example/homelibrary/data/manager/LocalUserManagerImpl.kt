package com.example.homelibrary.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.homelibrary.util.Constants
import com.example.homelibrary.util.Constants.USER_SETTINGS
import com.example.homelibrary.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocalUserManagerImpl @Inject constructor (
    private val context: Context
) : LocalUserManager {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data
            .catch { exception ->
                exception.printStackTrace()
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[PreferencesKeys.APP_ENTRY] ?: false
            }
    }

}

private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}