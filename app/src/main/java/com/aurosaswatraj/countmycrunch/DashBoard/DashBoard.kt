package com.aurosaswatraj.countmycrunch.DashBoard

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aurosaswatraj.countmycrunch.R

class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_ui)
        val window = this.window
        window.statusBarColor = Color.parseColor("#2f3640")




    }
// TODO: Implement a Cardview based UI DashBoard : https://www.geeksforgeeks.org/dashboard-ui-design-in-android/
// TODO: Implement a spotlight feature-> https://github.com/TakuSemba/Spotlight
}