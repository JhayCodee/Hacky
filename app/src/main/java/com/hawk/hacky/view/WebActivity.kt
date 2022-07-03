package com.hawk.hacky.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

import com.hawk.hacky.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // def cliente chrome
        binding.wb.webChromeClient = object : WebChromeClient() {

        }

        binding.wb.webViewClient = object : WebViewClient() {

        }

        val settings = binding.wb.settings
        settings.javaScriptEnabled = true

        val link: String? = intent.getStringExtra("link")

        if (link != null) {
            binding.wb.loadUrl(link)
        }
    }

    override fun onBackPressed() {

        if (binding.wb.canGoBack()) {
            binding.wb.goBack()
        }
        else {
            super.onBackPressed()
        }

    }

}