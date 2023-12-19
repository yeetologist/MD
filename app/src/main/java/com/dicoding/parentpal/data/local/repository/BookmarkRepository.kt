package com.dicoding.parentpal.data.local.repository

import androidx.lifecycle.LiveData
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkDatabase
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BookmarkRepository(private val bookmarkDatabase: BookmarkDatabase) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAllBookmark(email: String): LiveData<List<BookmarkEntity>> =
        bookmarkDatabase.bookmarkDao().getAllBookmarkNews(email)

    fun insert(bookmarkEntity: BookmarkEntity) {
        executorService.execute { bookmarkDatabase.bookmarkDao().insertNews(bookmarkEntity) }
    }

    fun delete(bookmarkEntity: BookmarkEntity) {
        executorService.execute { bookmarkDatabase.bookmarkDao().deleteNews(bookmarkEntity) }
    }

    fun update(bookmarkEntity: BookmarkEntity) {
        executorService.execute { bookmarkDatabase.bookmarkDao().updateNews(bookmarkEntity) }
    }

    fun getUserBookmarkByUrl(url: String, email: String): LiveData<List<BookmarkEntity>> =
        bookmarkDatabase.bookmarkDao().getUserBookmarkByUrl(url, email)

}