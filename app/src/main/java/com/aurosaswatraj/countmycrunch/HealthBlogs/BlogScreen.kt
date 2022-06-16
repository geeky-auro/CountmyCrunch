package com.aurosaswatraj.countmycrunch.HealthBlogs

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.amrdeveloper.lottiedialog.LottieDialog
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.activity_blog_screen.*

class BlogScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_screen)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        shimmer_anim.startShimmerAnimation()


        val blogLink:String? = if (savedInstanceState == null) {
            intent.extras?.getString("link")
        } else {
            savedInstanceState.getSerializable("link") as String?
        }


        showVlog.loadUrl(blogLink!!)
        showVlog.settings.javaScriptEnabled = true



        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.

        val dialog: LottieDialog = LottieDialog(this)
            .setAnimation(R.raw.loadng)
            .setAnimationRepeatCount(LottieDialog.INFINITE)
            .setAutoPlayAnimation(true)
            .setDialogBackground(Color.WHITE)
            .setCancelable(true)
        dialog.show()

        showVlog.webViewClient=object : WebViewClient(){

            override fun onPageFinished(view: WebView?, url: String?) {
                dialog.dismiss()
                shimmer_anim.stopShimmerAnimation()
            }
        }



        backbtno.setOnClickListener {
            startActivity(Intent(this,HealthVlogActivity::class.java))
            finish()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this,HealthVlogActivity::class.java))
        finish()

    }



}