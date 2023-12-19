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

    @Query("SELECT * from favorite WHERE email = :email")
    fun getAllBookmarkNews(email: String): LiveData<List<BookmarkEntity>>

    @Query("SELECT * FROM favorite WHERE email = :email ORDER BY timestamp DESC")
    fun getAllBookmarksSortedByTime(email: String): LiveData<List<BookmarkEntity>>

    @Query("SELECT * from favorite WHERE url = :url AND email = :email")
    fun getUserBookmarkByUrl(url: String, email: String): LiveData<List<BookmarkEntity>>
}