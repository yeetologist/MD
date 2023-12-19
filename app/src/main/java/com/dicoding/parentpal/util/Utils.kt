package com.dicoding.parentpal.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.remote.response.ArticlesItem
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun getOneMonthBeforeCurrentTime(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val oneMonthBefore = LocalDate.now().minusMonths(1)
    return oneMonthBefore.format(formatter)
}

fun ArticlesItem.toBookmarkEntity(email: String): BookmarkEntity {
    return BookmarkEntity(
        publishedAt = this.publishedAt,
        author = this.author,
        urlToImage = this.urlToImage,
        description = this.description,
        title = this.title,
        url = this.url,
        content = this.content,
        email = email
    )
}

fun BookmarkEntity.toArticlesItem(): ArticlesItem {
    return ArticlesItem(
        publishedAt = this.publishedAt,
        author = this.author,
        urlToImage = this.urlToImage,
        description = this.description,
        title = this.title,
        url = this.url,
        content = this.content
    )
}

fun showSnackbarShort(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}