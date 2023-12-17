package com.dicoding.parentpal.data.local.database.news

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.parentpal.data.remote.response.ArticlesItem

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<ArticlesItem>)

    @Query("SELECT * FROM news")
    fun getAllNews(): PagingSource<Int, ArticlesItem>

    @Query("DELETE FROM news")
    suspend fun deleteAll()
}