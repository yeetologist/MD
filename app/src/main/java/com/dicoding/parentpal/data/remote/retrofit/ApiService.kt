package com.dicoding.parentpal.data.remote.retrofit

import com.dicoding.parentpal.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getListNews(
        @Query("q") query: String = "Health",
        @Query("from") fromDate: String = "2023-12-1",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = "c3775a471c674664b618c6a70ae128e8",
        @Query("page") page: Int,
        @Query("size") size: Int
    ): NewsResponse
}