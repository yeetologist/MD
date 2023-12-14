package com.dicoding.parentpal.util

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.Glide
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