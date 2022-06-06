package com.aurosaswatraj.countmycrunch.DashBoard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.aurosaswatraj.countmycrunch.Dialogs.UserManualDialog
import com.aurosaswatraj.countmycrunch.Fooding.Foodz
import com.aurosaswatraj.countmycrunch.HealthBlogs.HealthVlogActivity
import com.aurosaswatraj.countmycrunch.HealthBlogs.HealthVlogs
import com.aurosaswatraj.countmycrunch.MainActivity
import com.aurosaswatraj.countmycrunch.R
import com.thecode.aestheticdialogs.*
import kotlinx.android.synthetic.main.dashboard_ui.*
import me.toptas.fancyshowcase.FancyShowCaseView

class DashBoard : AppCompatActivity() {

    private var fragment_no=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_ui)
        val window = this.window
        window.statusBarColor = Color.parseColor("#2f3640")
        fragment_no=0

        showSomeSpotLight()


        helpme.setOnClickListener {
            UserManualDialog().showDialog(this)
        }

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

        health_vlogs.setOnClickListener {
            intent=Intent(applicationContext,HealthVlogActivity::class.java)
            startActivity(intent)
        }



        chronometer.setOnClickListener {
            cominGsoon()
        }

        backB.setOnClickListener {
            finishAffinity()
        }


    }

    private fun showSomeSpotLight(){

        FancyShowCaseView.Builder(this)
            .focusOn(helpme)
            .title("CountMyCrunch HelpDesk\nGet Well in a personal way What is CountMyCrunch All about..! Let’s Know more about the insights of our application.")
            .titleStyle(R.style.spotLightTitle, Gravity.CENTER_VERTICAL)
            .build()
            .show()
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

}