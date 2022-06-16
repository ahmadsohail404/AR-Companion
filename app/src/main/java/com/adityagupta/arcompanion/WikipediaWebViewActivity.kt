package com.adityagupta.arcompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.adityagupta.arcompanion.databinding.ActivityMeaningBinding
import com.adityagupta.arcompanion.databinding.ActivityWikipediaWebViewBinding

class WikipediaWebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWikipediaWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wikipedia_web_view)

        val word = intent.getStringExtra("title")
        binding = ActivityWikipediaWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var webView = binding.wikiWebView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);


        webView.loadUrl("https://en.m.wikipedia.org/wiki/" + word);



/*
        binding.wikiWebView.loadUrl("https://en.m.wikipedia.org/wiki/" + word)
*/
    }
}