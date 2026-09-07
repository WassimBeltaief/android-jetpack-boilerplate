package com.example.boilerplate.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    private object Keys {
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val IS_ONBOARDED = booleanPreferencesKey("is_onboarded")
    }

    val authToken: Flow<String?> = dataStore.data.map { it[Keys.AUTH_TOKEN] }

    val isOnboarded: Flow<Boolean> = dataStore.data.map { it[Keys.IS_ONBOARDED] ?: false }

    suspend fun setAuthToken(token: String) {
        dataStore.edit { it[Keys.AUTH_TOKEN] = token }
    }

    suspend fun setOnboarded(onboarded: Boolean) {
        dataStore.edit { it[Keys.IS_ONBOARDED] = onboarded }
    }

    suspend fun clearAll() {
        dataStore.edit { it.clear() }
    }
}
