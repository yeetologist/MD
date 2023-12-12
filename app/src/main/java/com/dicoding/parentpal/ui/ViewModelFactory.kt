package com.dicoding.parentpal.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.parentpal.data.di.Injection
import com.dicoding.parentpal.ui.news.NewsViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(Injection.provideRepository(context)) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}