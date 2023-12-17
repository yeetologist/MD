package com.dicoding.parentpal.data.local.database.bookmark

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(fav: BookmarkEntity)

    @Update
    fun updateNews(fav: BookmarkEntity)

    @Delete
    fun deleteNews(fav: BookmarkEntity)

    @Query("SELECT * from favorite")
    fun getAllBookmarkNews(): LiveData<List<BookmarkEntity>>

    @Query("SELECT * FROM favorite ORDER BY timestamp DESC")
    fun getAllBookmarksSortedByTime(): LiveData<List<BookmarkEntity>>

    @Query("SELECT * from favorite WHERE url = :url")
    fun getUserBookmarkByUrl(url: String): LiveData<List<BookmarkEntity>>
}