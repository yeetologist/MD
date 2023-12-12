package com.dicoding.parentpal.data.local.paging

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dicoding.parentpal.data.local.database.NewsDatabase
import com.dicoding.parentpal.data.remote.response.ArticlesItem
import com.dicoding.parentpal.data.remote.retrofit.ApiService

class NewsRepository(private val newsDatabase: NewsDatabase, private val apiService: ApiService) {
    fun getNews(): LiveData<PagingData<ArticlesItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 1
            ),
            remoteMediator = NewsRemoteMediator(newsDatabase, apiService),
            pagingSourceFactory = {
//                NewsPagingSource(apiService)
                newsDatabase.newsDao().getAllNews()
            }
        ).liveData
    }
}