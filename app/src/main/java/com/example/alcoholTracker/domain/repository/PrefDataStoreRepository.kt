package com.example.alcoholTracker.domain.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.alcoholTracker.common.PrefKeys.IS_DARK_MODE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

suspend fun setDarkMode(dataStore: DataStore<Preferences>, isDarkMode: Boolean) {
    dataStore.edit { pref ->
        pref[IS_DARK_MODE_KEY] = isDarkMode
    }
}

suspend fun getDarkMode(dataStore: DataStore<Preferences>): Flow<Boolean> {
    return dataStore.data.map { pref ->
        pref[IS_DARK_MODE_KEY] ?: false
    }
}