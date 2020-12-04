package com.decimalab.simpletask.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * Created by Shakil Ahmed Shaj on 22,September,2020.
 * shakilahmedshaj@gmail.com
 */
class UserPreferences(context: Context) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    companion object {
        const val PREFERENCE_NAME = "my_data_store"
    }

    private object PreferenceKeys {
        val ACCESS_TOKEN = preferencesKey<String>("key_access_token")
        val TOKEN_ID = preferencesKey<String>("key_token_id")
        val USER_ID = preferencesKey<String>("key_user_id")
        val USER_NAME = preferencesKey<String>("key_user_name")
        val USER_EMAIL = preferencesKey<String>("key_user_email")
    }

    init {
        dataStore = applicationContext.createDataStore(
            name = PREFERENCE_NAME
        )
    }

    val getAccessToken: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.ACCESS_TOKEN] ?: "null"
            myName
        }

    val getTokenId: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.TOKEN_ID] ?: "none"
            myName
        }

    val getUserID: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.USER_ID] ?: "none"
            myName
        }

    val getUserName: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.USER_NAME] ?: "none"
            myName
        }

    val getUserEmail: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.USER_EMAIL] ?: "none"
            myName
        }

    suspend fun saveAccessToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.ACCESS_TOKEN] = authToken
        }
    }

    suspend fun saveTokenId(authToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.TOKEN_ID] = authToken
        }
    }

    suspend fun saveUserID(authToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.USER_ID] = authToken
        }
    }

    suspend fun saveUserName(authToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.USER_NAME] = authToken
        }
    }

    suspend fun saveUserEmail(authToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.USER_EMAIL] = authToken
        }
    }

}