package com.dicoding.parentpal.data.di

import android.content.Context
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkDatabase
import com.dicoding.parentpal.data.local.database.news.NewsDatabase
import com.dicoding.parentpal.data.local.repository.BookmarkRepository
import com.dicoding.parentpal.data.local.repository.NewsRepository
import com.dicoding.parentpal.data.remote.retrofit.ApiConfig

object Injection {
    fun provideNewsRepository(context: Context): NewsRepository {
        val database = NewsDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return NewsRepository(database, apiService)
    }

    fun provideBookmarkRepository(context: Context): BookmarkRepository {
        val database = BookmarkDatabase.getDatabase(context)
        return BookmarkRepository(database)
    }
}