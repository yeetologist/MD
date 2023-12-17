package com.dicoding.parentpal.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.local.repository.BookmarkRepository

class ProfileViewModel(private val bookmarkRepository: BookmarkRepository) : ViewModel() {
    fun getAllBookmarks(): LiveData<List<BookmarkEntity>> = bookmarkRepository.getAllBookmark()

}