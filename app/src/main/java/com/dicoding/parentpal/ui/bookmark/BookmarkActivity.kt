package com.dicoding.parentpal.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.databinding.ActivityBookmarkBinding
import com.dicoding.parentpal.ui.ViewModelFactory
import com.dicoding.parentpal.ui.news.DetailNewsActivity
import com.dicoding.parentpal.util.PreferenceManager

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding

    private val bookmarkViewModel: BookmarkViewModel by viewModels {
        ViewModelFactory(this)
    }

    private val bookmarkAdapter: BookmarkAdapter by lazy {
        BookmarkAdapter(::onNewsClick)
    }

    private lateinit var preferenceManager: PreferenceManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        binding.rvListBookmark.layoutManager = LinearLayoutManager(this)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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
        preferenceManager.getPreferences()?.let { pref ->
            bookmarkViewModel.getAllBookmarks(pref.email).observe(this) {
                adapter.submitList(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}