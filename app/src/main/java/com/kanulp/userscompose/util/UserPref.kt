package com.kanulp.userscompose.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPref(private val context: Context) {

    companion object {
        private const val PREF = "userpref"
        private const val USER_KEY = "user"
        private const val PASSWORD_KEY = "password"

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREF)
        val USER_DATA = stringPreferencesKey(USER_KEY)
        val PASSWORD_DATA = stringPreferencesKey(PASSWORD_KEY)
    }

    val getUser: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_DATA] ?: ""
        }

    suspend fun saveUser(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_DATA] = name
        }
    }

    val getPassword: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PASSWORD_DATA] ?: ""
        }

    suspend fun savePassword(password: String) {
        context.dataStore.edit { preferences ->
            preferences[PASSWORD_DATA] = password
        }
    }
}