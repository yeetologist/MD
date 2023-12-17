package com.dicoding.parentpal.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.parentpal.data.di.Injection
import com.dicoding.parentpal.ui.bookmark.BookmarkViewModel
import com.dicoding.parentpal.ui.news.NewsViewModel
import com.dicoding.parentpal.ui.profile.ProfileViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(
                    Injection.provideNewsRepository(context),
                    Injection.provideBookmarkRepository(context),
                    Injection.provideHistoryRepository(context)
                ) as T
            }

            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(
                    Injection.provideBookmarkRepository(context),
                    Injection.provideHistoryRepository(context),
                ) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(
                    Injection.provideBookmarkRepository(context),
                    Injection.provideHistoryRepository(context)
                ) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}