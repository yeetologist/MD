package com.dicoding.parentpal.util

import android.content.Context
import com.dicoding.parentpal.data.remote.response.ResponseItem
import com.google.gson.Gson

class PreferenceManager(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("ResponseItemPref", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun savePreferences(myPreferences: ResponseItem) {
        val json = gson.toJson(myPreferences)
        sharedPreferences.edit().putString("ResponseItemKey", json).apply()
    }

    fun getPreferences(): ResponseItem? {
        val json = sharedPreferences.getString("ResponseItemKey", null)
        return gson.fromJson(json, ResponseItem::class.java)
    }

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
        // Alternatively, you can use commit() instead of apply() for immediate effect
        // sharedPreferences.edit().clear().commit()
    }
}