package com.dicoding.parentpal.data.local.repository

import androidx.lifecycle.LiveData
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.local.database.bookmark.HistoryDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository(private val historyDatabase: HistoryDatabase) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAllBookmark(email: String): LiveData<List<BookmarkEntity>> =
        historyDatabase.bookmarkDao().getAllBookmarkNews(email)

    fun getAllBookmarksSortedByTime(email: String): LiveData<List<BookmarkEntity>> =
        historyDatabase.bookmarkDao().getAllBookmarksSortedByTime(email)

    fun insert(bookmarkEntity: BookmarkEntity) {
        executorService.execute { historyDatabase.bookmarkDao().insertNews(bookmarkEntity) }
    }

    fun delete(bookmarkEntity: BookmarkEntity) {
        executorService.execute { historyDatabase.bookmarkDao().deleteNews(bookmarkEntity) }
    }

    fun update(bookmarkEntity: BookmarkEntity) {
        executorService.execute { historyDatabase.bookmarkDao().updateNews(bookmarkEntity) }
    }

    fun getUserBookmarkByUrl(url: String, email: String): LiveData<List<BookmarkEntity>> =
        historyDatabase.bookmarkDao().getUserBookmarkByUrl(url, email)

}