package com.aurosaswatraj.countmycrunch.manuals

import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.aurosaswatraj.countmycrunch.MainActivity
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.activity_user_manual.*


class UserManual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_manual)

        web.loadUrl("https://medium.com/@aurosaswatraj/user-manual-of-calorie-counter-972df34a8009")
        web.getSettings().setJavaScriptEnabled(true)

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        web.setWebViewClient(WebViewClient())

        DONEi.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}