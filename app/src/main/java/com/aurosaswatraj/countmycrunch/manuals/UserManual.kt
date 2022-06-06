package com.aurosaswatraj.countmycrunch.manuals


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.amrdeveloper.lottiedialog.LottieDialog
import com.aurosaswatraj.countmycrunch.MainActivity
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.activity_user_manual.*


class UserManual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_manual)
        val newString:String? = if (savedInstanceState == null) {
            intent.extras?.getString("STRING_I_NEED")
        } else {
            savedInstanceState.getSerializable("STRING_I_NEED") as String?
        }

//        Need to customize the url according to the set button

        web.loadUrl(newString!!)
        web.settings.javaScriptEnabled = true

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

        web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                dialog.dismiss()
            }
        }
        DONEi.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }


    }
}