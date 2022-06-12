package com.aurosaswatraj.countmycrunch.Chronometer

import android.animation.ObjectAnimator
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.aurosaswatraj.countmycrunch.Dialogs.UserDarkModeDialog
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.activity_chronometer2.*

class Chronometer_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronometer2)
        val window = this.window
        window.statusBarColor = Color.parseColor("#2f3640")
        var darkModeDialog = UserDarkModeDialog()
        darkModeDialog.darkModeDialog(this, this)
        requestedOrientation =ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // Fixed portrait orientation

        val counter = 0
        var objectAnimator = ObjectAnimator.ofFloat(i_canchor, "rotation", 0f, 360f)
        objectAnimator.setDuration(4000)
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE)

        btn_stop_.alpha= 0F

        btn_start_.setOnClickListener {

//                passing Animation
            objectAnimator.start()
//                icanchor.startAnimation(roundingalone);
            //                icanchor.startAnimation(roundingalone);
            btn_stop_.animate().alpha(1f).translationY(-80f).setDuration(300).start()
            btn_start_.animate().alpha(0f).setDuration(300).start()
//                start time
            //   start time
            time_Here.base = SystemClock.elapsedRealtime()
            time_Here.start()
        }

        btn_stop_.setOnClickListener {
            val elapsedtime: Long = SystemClock.elapsedRealtime() - time_Here.getBase()
            objectAnimator.pause()
            btn_start_.animate().alpha(1f).translationY(-80f).setDuration(300).start()
            btn_stop_.animate().alpha(0f).setDuration(300).start()
            time_Here.stop()
        }



    }
}