package com.dicoding.parentpal.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.local.repository.BookmarkRepository

class BookmarkViewModel(private val bookmarkRepository: BookmarkRepository) : ViewModel() {
    fun getAllBookmarks(): LiveData<List<BookmarkEntity>> = bookmarkRepository.getAllBookmark()
}