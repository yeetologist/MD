package com.dicoding.parentpal.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.local.repository.BookmarkRepository
import com.dicoding.parentpal.data.local.repository.HistoryRepository
import com.dicoding.parentpal.data.local.repository.NewsRepository
import com.dicoding.parentpal.data.remote.response.ArticlesItem

class NewsViewModel(
    newsRepository: NewsRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    val news: LiveData<PagingData<ArticlesItem>> =
        newsRepository.getNews().cachedIn(viewModelScope)

    private val _detail = MutableLiveData<ArticlesItem>()
    val detail: LiveData<ArticlesItem> = _detail

    fun insertBookmark(favEntity: BookmarkEntity) {
        bookmarkRepository.insert(favEntity)
    }

    fun deleteBookmark(favEntity: BookmarkEntity) {
        bookmarkRepository.delete(favEntity)
    }

    fun insertHistory(favEntity: BookmarkEntity) {
        historyRepository.insert(favEntity)
    }

    fun deleteHistory(favEntity: BookmarkEntity) {
        historyRepository.delete(favEntity)
    }

    fun getBookmarkByUrl(url: String, email: String): LiveData<List<BookmarkEntity>> {
        return bookmarkRepository.getUserBookmarkByUrl(url, email)
    }
}