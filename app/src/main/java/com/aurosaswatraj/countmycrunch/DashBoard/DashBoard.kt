package com.aurosaswatraj.countmycrunch.DashBoard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.aurosaswatraj.countmycrunch.Fooding.Foodz
import com.aurosaswatraj.countmycrunch.MainActivity
import com.aurosaswatraj.countmycrunch.R
import com.thecode.aestheticdialogs.*
import kotlinx.android.synthetic.main.dashboard_ui.*

class DashBoard : AppCompatActivity() {

    private var fragment_no=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_ui)
        val window = this.window
        window.statusBarColor = Color.parseColor("#2f3640")
        fragment_no=0

        bmi_calc.setOnClickListener {
            fragment_no=1
            intent = Intent(applicationContext, MainActivity::class.java).putExtra("id",fragment_no)
            startActivity(intent)
        }

        calorie_count.setOnClickListener {
            fragment_no=2
            intent = Intent(applicationContext, MainActivity::class.java).putExtra("id",fragment_no)
            startActivity(intent)
        }

        foodie.setOnClickListener {
            intent = Intent(applicationContext, Foodz::class.java)
            startActivity(intent)
        }

        calorie_track.setOnClickListener {
            fragment_no=3
            intent = Intent(applicationContext, MainActivity::class.java).putExtra("id",fragment_no)
            startActivity(intent)
        }



        chronometer.setOnClickListener {
            cominGsoon()
        }













    }



    private fun cominGsoon(){
        AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.WARNING)
            .setTitle("Coming Soon")
            .setMessage("Work in Progress!")
            .setCancelable(true)
            .setDarkMode(true)
            .setGravity(Gravity.CENTER)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                    //actions...
                }
            })
            .show()
    }
// TODO: Implement a Cardview based UI DashBoard : https://www.geeksforgeeks.org/dashboard-ui-design-in-android/
// TODO: Implement a spotlight feature-> https://github.com/TakuSemba/Spotlight
}