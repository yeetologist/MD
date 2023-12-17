package com.dicoding.parentpal.ui.news

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.parentpal.R
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.data.remote.response.ArticlesItem
import com.dicoding.parentpal.databinding.ActivityDetailNewsBinding
import com.dicoding.parentpal.ui.ViewModelFactory
import com.dicoding.parentpal.util.toArticlesItem
import com.dicoding.parentpal.util.toBookmarkEntity
import com.google.android.material.snackbar.Snackbar

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding

    private val newsViewModel: NewsViewModel by viewModels {
        ViewModelFactory(this)
    }

    private var isBookmarked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_ARTICLE_NEWS, ArticlesItem::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_ARTICLE_NEWS)
        }

        if (article == null) {
            article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(EXTRA_BOOKMARK_NEWS, BookmarkEntity::class.java)
                    ?.toArticlesItem()
            } else {
                intent.getParcelableExtra<BookmarkEntity>(EXTRA_BOOKMARK_NEWS)?.toArticlesItem()
            }
        }

        val webView = findViewById<WebView>(R.id.webView)

        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (article != null) {
                    view.loadUrl("javascript:alert('${article.title}')")
                }
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: android.webkit.JsResult
            ): Boolean {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                result.confirm()
                return true
            }
        }

        if (article != null) {
            webView.loadUrl(article.url)
            newsViewModel.getBookmarkByUrl(article.url).observe(this@DetailNewsActivity) { entity ->
                isBookmarked = entity.isNotEmpty()
                if (entity.isNotEmpty()) binding.fabBookmark.imageTintList =
                    ColorStateList.valueOf(Color.rgb(255, 50, 50))
                else binding.fabBookmark.imageTintList =
                    ColorStateList.valueOf(Color.rgb(255, 255, 255))
            }
            setupFab(article)
        }

    }

    private fun setupFab(article: ArticlesItem) {

        binding.fabBookmark.setOnClickListener {
            if (!isBookmarked) {
                newsViewModel.insert(article.toBookmarkEntity())
            } else {
                newsViewModel.delete(article.toBookmarkEntity())
            }
        }
    }

    companion object {
        const val EXTRA_ARTICLE_NEWS = "EXTRA_ARTICLE_NEWS"
        const val EXTRA_BOOKMARK_NEWS = "EXTRA_BOOKMARK_NEWS"
    }
}