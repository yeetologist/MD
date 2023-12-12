package com.dicoding.parentpal.data.di

import android.content.Context
import com.dicoding.parentpal.data.local.database.NewsDatabase
import com.dicoding.parentpal.data.local.paging.NewsRepository
import com.dicoding.parentpal.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val database = NewsDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return NewsRepository(database, apiService)
    }
}