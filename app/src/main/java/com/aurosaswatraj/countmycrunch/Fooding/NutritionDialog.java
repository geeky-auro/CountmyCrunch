package com.aurosaswatraj.countmycrunch.Fooding;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.aurosaswatraj.countmycrunch.R;
import com.squareup.picasso.Picasso;

public class NutritionDialog extends Dialog implements View.OnClickListener{


    public String id;
    public Dialog dialog;
    public ImageView nutrition_imageView;

    public NutritionDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Window window = this.getWindow();
        window.setStatusBarColor(Color.parseColor("#C51162"));

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.nutrition_dialog);
        nutrition_imageView = (ImageView) findViewById(R.id.imageView_nutrition);
        Picasso.get().load("https://api.spoonacular.com/recipes/641166/nutritionLabel.png");
        nutrition_imageView.setOnClickListener(this);

    }
}
