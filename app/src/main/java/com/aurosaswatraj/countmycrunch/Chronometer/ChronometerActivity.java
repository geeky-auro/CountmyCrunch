package com.aurosaswatraj.countmycrunch.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.aurosaswatraj.countmycrunch.Dialogs.UserDarkModeDialog;
import com.aurosaswatraj.countmycrunch.R;


public class ChronometerActivity extends AppCompatActivity {



   private  Button btnstart,btnstop;
    private ImageView icanchor;
    private Chronometer timeHere;

    private ObjectAnimator objectAnimator;
    UserDarkModeDialog darkModeDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);
        darkModeDialog=new UserDarkModeDialog();
        darkModeDialog.darkModeDialog(this,this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Fixed portrait orientation



        Window window = this.getWindow();
        window.setStatusBarColor(Color.parseColor("#2f3640"));

        btnstart=findViewById(R.id.btn_start);
        btnstop=findViewById(R.id.btn_stop);
        icanchor=findViewById(R.id.icanchor);
        timeHere=findViewById(R.id.timeHere);




        objectAnimator=ObjectAnimator.ofFloat(icanchor,"rotation",0f,360f);
        objectAnimator.setDuration(4000);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//        For rotation..! https://developer.android.com/guide/topics/graphics/prop-animation.html#views



//        create optional aniamtion
        btnstop.setAlpha(0);


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                passing Animation
                objectAnimator.start();
//                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();
//                start time
                timeHere.setBase(SystemClock.elapsedRealtime());
                timeHere.start();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectAnimator.pause();
                btnstart.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstop.animate().alpha(0).setDuration(300).start();
                timeHere.stop();
            }
        });





    }
}