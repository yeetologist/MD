package com.dicoding.parentpal.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable

@Parcelize
data class Source(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null
) : Parcelable

@Parcelize
@Entity(tableName = "news")
data class ArticlesItem(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

//	@field:SerializedName("source")
//	val source: Source? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @PrimaryKey
    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("content")
    val content: String? = null
) : Parcelable
