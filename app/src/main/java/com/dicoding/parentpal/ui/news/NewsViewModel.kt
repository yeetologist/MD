package com.dicoding.parentpal.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.parentpal.data.local.paging.NewsRepository
import com.dicoding.parentpal.data.remote.response.ArticlesItem

class NewsViewModel(newsRepository: NewsRepository) : ViewModel() {
    val news: LiveData<PagingData<ArticlesItem>> =
        newsRepository.getNews().cachedIn(viewModelScope)
}