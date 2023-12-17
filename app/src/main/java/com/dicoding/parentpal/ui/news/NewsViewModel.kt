package com.dicoding.parentpal.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.local.repository.BookmarkRepository
import com.dicoding.parentpal.data.local.repository.NewsRepository
import com.dicoding.parentpal.data.remote.response.ArticlesItem

class NewsViewModel(
    newsRepository: NewsRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    val news: LiveData<PagingData<ArticlesItem>> =
        newsRepository.getNews().cachedIn(viewModelScope)

    private val _detail = MutableLiveData<ArticlesItem>()
    val detail: LiveData<ArticlesItem> = _detail

    fun insert(favEntity: BookmarkEntity) {
        bookmarkRepository.insert(favEntity)
    }

    fun delete(favEntity: BookmarkEntity) {
        bookmarkRepository.delete(favEntity)
    }

    fun getBookmarkByUrl(url: String): LiveData<List<BookmarkEntity>> {
        return bookmarkRepository.getUserBookmarkByUrl(url)
    }
}