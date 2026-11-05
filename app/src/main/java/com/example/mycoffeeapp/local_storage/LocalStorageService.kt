package com.example.mycoffeeapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.mycoffeeapp.local_storage.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalStorageService(
    private val context: Context
) {

    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_FIRST_LAUNCH_KEY =
            booleanPreferencesKey("is_first_launch")
        private val THEME_KEY = stringPreferencesKey("theme")
        private val AGE_KEY = intPreferencesKey("age")
    }

    // SAVE STRING
    suspend fun saveString(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    // GET STRING
    fun getString(key: String): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]
        }
    }

    // SAVE BOOLEAN
    suspend fun saveBoolean(key: String, value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    // GET BOOLEAN
    fun getBoolean(key: String): Flow<Boolean?> {
        return context.dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)]
        }
    }

    // SAVE INT
    suspend fun saveInt(key: String, value: Int) {
        context.dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    // GET INT
    fun getInt(key: String): Flow<Int?> {
        return context.dataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)]
        }
    }

    // REMOVE KEY
    suspend fun remove(key: String) {
        context.dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
            preferences.remove(booleanPreferencesKey(key))
            preferences.remove(intPreferencesKey(key))
        }
    }

    // CLEAR ALL
    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}