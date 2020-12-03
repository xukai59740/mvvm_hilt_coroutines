package com.eight_centimeter.android.mvvm_coroutines_hilt.data.common

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    private fun <T> get(key: String, clazz: Class<T>): T =
            when (clazz) {
                String::class.java -> sharedPreferences.getString(key, "")
                Boolean::class.java -> sharedPreferences.getBoolean(key, false)
                Float::class.java -> sharedPreferences.getFloat(key, 0f)
                Double::class.java -> sharedPreferences.getFloat(key, 0f)
                Int::class.java -> sharedPreferences.getInt(key, -1)
                Long::class.java -> sharedPreferences.getLong(key, -1L)
                else -> null
            } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    //////////////////////// Logic //////////////////////

    fun saveApiToken(token: String) {
        put(API_TOKEN, token)
    }

    fun getApiToken(): String? {
        return get(API_TOKEN, String::class.java)
    }

    companion object {
        /// group 1
        const val PREFS_NAME = "SharedPreferences"
        const val API_TOKEN = "api_token"
    }
}
