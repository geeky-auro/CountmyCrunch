package com.aurosaswatraj.countmycrunch.DashBoard

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aurosaswatraj.countmycrunch.Chronometer.Chronometer_Activity
import com.aurosaswatraj.countmycrunch.Dialogs.UserDarkModeDialog
import com.aurosaswatraj.countmycrunch.Dialogs.UserManualDialog
import com.aurosaswatraj.countmycrunch.Fooding.Foodz
import com.aurosaswatraj.countmycrunch.HealthBlogs.HealthVlogActivity
import com.aurosaswatraj.countmycrunch.MainActivity
import com.aurosaswatraj.countmycrunch.R
import com.thecode.aestheticdialogs.*
import kotlinx.android.synthetic.main.dashboard_ui.*
import me.toptas.fancyshowcase.FancyShowCaseView

class DashBoard : AppCompatActivity() {

    private var fragment_no=0

    private val rotateOpen:Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    private val rotateClose:Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim) }
    private val fromBottom:Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim) }
    private val toBottom:Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim) }


    private var clicked=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_ui)
        val window = this.window
        requestedOrientation =ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // Fixed portrait orientation


        scheduler_btn.setOnClickListener {
            onAddButtonClicked()
        }

        setAlarm.setOnClickListener {
            Toast.makeText(this,"Set Alarm",Toast.LENGTH_SHORT).show()
        }
        cancelAlarm.setOnClickListener {
            Toast.makeText(this,"Cancel Alarm",Toast.LENGTH_SHORT).show()
        }

        val darkModeDialog=UserDarkModeDialog()
        darkModeDialog.darkModeDialog(this,this)
        window.statusBarColor = Color.parseColor("#2f3640")
        fragment_no=0

        showSomeSpotLight()


        helpme.setOnClickListener {
            UserManualDialog().showDialog(this)
        }

        bmi_calc.setOnClickListener {
            fragment_no=1
            intent = Intent(applicationContext, MainActivity::class.java).putExtra("id",fragment_no).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        calorie_count.setOnClickListener {
            fragment_no=2
            intent = Intent(applicationContext, MainActivity::class.java).putExtra("id",fragment_no).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        foodie.setOnClickListener {
            intent = Intent(applicationContext, Foodz::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        calorie_track.setOnClickListener {
            fragment_no=3
            intent = Intent(applicationContext, MainActivity::class.java).putExtra("id",fragment_no).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        health_vlogs.setOnClickListener {
            intent=Intent(applicationContext,HealthVlogActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }



        chronometer.setOnClickListener {
            intent=Intent(applicationContext,Chronometer_Activity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        backB.setOnClickListener {
            finishAffinity()
        }


    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked


    }

    private fun setVisibility(clicked:Boolean) {
       if (!clicked){
           setAlarm.visibility=View.VISIBLE
           cancelAlarm.visibility=View.VISIBLE
       }
        else{
           setAlarm.visibility=View.INVISIBLE
           cancelAlarm.visibility=View.INVISIBLE
       }
    }

    private fun setAnimation(clicked:Boolean) {
        if (!clicked){
            setAlarm.startAnimation(fromBottom)
            cancelAlarm.startAnimation(fromBottom)
            scheduler_btn.startAnimation(rotateOpen)
        }
        else{
            setAlarm.startAnimation(toBottom)
            cancelAlarm.startAnimation(toBottom)
            scheduler_btn.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean){
        if (!clicked){
            setAlarm.isClickable=true
            cancelAlarm.isClickable=true
        }
        else{
            setAlarm.isClickable=false
            cancelAlarm.isClickable=false
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