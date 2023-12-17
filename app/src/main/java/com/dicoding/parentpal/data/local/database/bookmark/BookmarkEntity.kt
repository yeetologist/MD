package com.dicoding.parentpal.data.local.database.bookmark

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite")
@Parcelize
data class BookmarkEntity(
    @field:ColumnInfo("publishedAt")
    val publishedAt: String? = null,

    @field:ColumnInfo("author")
    val author: String? = null,

    @field:ColumnInfo("urlToImage")
    val urlToImage: String? = null,

    @field:ColumnInfo("description")
    val description: String? = null,

    @field:ColumnInfo("title")
    val title: String? = null,

    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo("url")
    val url: String,

    @field:ColumnInfo("content")
    val content: String? = null,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis()

) : Parcelable