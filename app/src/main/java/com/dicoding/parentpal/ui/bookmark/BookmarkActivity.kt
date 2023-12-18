package com.dicoding.parentpal.ui.bookmark

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.databinding.ActivityBookmarkBinding
import com.dicoding.parentpal.ui.ViewModelFactory
import com.dicoding.parentpal.ui.news.DetailNewsActivity

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding

    private val bookmarkViewModel: BookmarkViewModel by viewModels {
        ViewModelFactory(this)
    }

    private val bookmarkAdapter: BookmarkAdapter by lazy {
        BookmarkAdapter(::onNewsClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvListBookmark.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun onNewsClick(bookmarkEntity: BookmarkEntity) {
        val intent = Intent(this@BookmarkActivity, DetailNewsActivity::class.java)
        intent.putExtra(DetailNewsActivity.EXTRA_BOOKMARK_NEWS, bookmarkEntity)
        startActivity(intent)
    }

    private fun getData() {
        val adapter = bookmarkAdapter
        binding.rvListBookmark.adapter = adapter
        bookmarkViewModel.getAllBookmarks().observe(this) {
            adapter.submitList(it)
        }
    }
}