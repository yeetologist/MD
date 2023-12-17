package com.dicoding.parentpal.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.local.repository.BookmarkRepository
import com.dicoding.parentpal.data.local.repository.HistoryRepository

class ProfileViewModel(
    private val bookmarkRepository: BookmarkRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    fun getAllBookmarks(): LiveData<List<BookmarkEntity>> = bookmarkRepository.getAllBookmark()

    fun getHistory(): LiveData<List<BookmarkEntity>> =
        historyRepository.getAllBookmarksSortedByTime()

}