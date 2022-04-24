package com.aurosaswatraj.countmycrunch.manuals

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.aurosaswatraj.countmycrunch.MainActivity
import com.aurosaswatraj.countmycrunch.R
import com.thecode.aestheticdialogs.*
import kotlinx.android.synthetic.main.activity_user_manual.*


class UserManual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_manual)

        web.loadUrl("https://medium.com/@aurosaswatraj/user-manual-of-calorie-counter-972df34a8009")
        web.settings.javaScriptEnabled = true

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.



        web.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {

            }
        })
        DONEi.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }


    }
}