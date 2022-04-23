package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class UserManager(val context: Context) {

    val Context.dataUser: DataStore<Preferences> by preferencesDataStore(name = "user")

    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
        private val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        private val USER_AUTHENTICATED_KEY = booleanPreferencesKey("USER_AUTHENTICATED")
    }

    suspend fun saveDataUser(name: String, age: Int, authenticated: Boolean) {
        context.dataUser.edit {
            it[USER_NAME_KEY] = name
            it[USER_AGE_KEY] = age
            it[USER_AUTHENTICATED_KEY] = authenticated
        }
    }

    suspend fun readDataUser(): User {
        val prefs = context.dataUser.data.first()

        return User(
            name = prefs[USER_NAME_KEY] ?: "",
            age = prefs[USER_AGE_KEY] ?: 0,
            authenticated = prefs[USER_AUTHENTICATED_KEY] ?: false
        )
    }

}