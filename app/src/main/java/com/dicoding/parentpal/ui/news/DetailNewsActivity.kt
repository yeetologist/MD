package com.dicoding.parentpal.ui.news

import android.os.Build
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.parentpal.R
import com.dicoding.parentpal.data.remote.response.ArticlesItem
import com.dicoding.parentpal.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_NEWS, ArticlesItem::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_NEWS)
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
                Toast.makeText(this@DetailNewsActivity, message, Toast.LENGTH_LONG).show()
                result.confirm()
                return true
            }
        }

        if (article != null) {
            webView.loadUrl(article.url)
        }

    }

    companion object {
        const val EXTRA_NEWS = "EXTRA_NEWS"
    }
}