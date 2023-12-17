package com.dicoding.parentpal.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.local.repository.BookmarkRepository
import com.dicoding.parentpal.data.local.repository.HistoryRepository

class BookmarkViewModel(
    private val bookmarkRepository: BookmarkRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    fun getAllBookmarks(): LiveData<List<BookmarkEntity>> = bookmarkRepository.getAllBookmark()
    fun getHistory(): LiveData<List<BookmarkEntity>> =
        historyRepository.getAllBookmarksSortedByTime()
}