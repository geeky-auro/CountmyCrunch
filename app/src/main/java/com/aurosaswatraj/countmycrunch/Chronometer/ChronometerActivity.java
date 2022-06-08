package com.aurosaswatraj.countmycrunch.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.aurosaswatraj.countmycrunch.R;


public class ChronometerActivity extends AppCompatActivity {



    Button btnstart,btnstop;
    ImageView icanchor;
    Animation roundingalone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);

        btnstart=findViewById(R.id.btn_start);
        btnstop=findViewById(R.id.btn_stop);
        icanchor=findViewById(R.id.icanchor);

//        create optional aniamtion
        btnstop.setAlpha(0);



//        Load Animation
        roundingalone= AnimationUtils.loadAnimation(this,R.anim.roundingalone);

//                Set fonts
        Typeface MMedium=Typeface.createFromAsset(getAssets(),"fonts/mmedium.ttf");

        btnstart.setTypeface(MMedium);
        btnstop.setTypeface(MMedium);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                passing Animation
                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();
            }
        });





    }
}