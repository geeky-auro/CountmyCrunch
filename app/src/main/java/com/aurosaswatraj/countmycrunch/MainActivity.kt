package com.aurosaswatraj.countmycrunch

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.CalorieCounter.FoodAdapter
import com.aurosaswatraj.countmycrunch.CalorieCounter.FoodItems
import com.aurosaswatraj.countmycrunch.CalorieCounter.SelectListener

import kotlinx.android.synthetic.main.calorie_counter_u_i.*
import kotlinx.android.synthetic.main.calorie_counter_u_i.btn_boy
import kotlinx.android.synthetic.main.calorie_counter_u_i.btn_girl
import kotlinx.android.synthetic.main.calorie_counter_u_i.submit_button
import kotlinx.android.synthetic.main.fragment_b_m_i_finder.*
import java.math.BigDecimal


private const val TAG="MainActivity"
class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.buttom_nev_menu, menu)
        return true
    }



}

/** Monitor your health in your hands. */
/**
 * Fat Loss = 12-13 calories per pound of body weight
 * Maintenance = 15-16 calories per #
 * Weight Gain = 18-19 calories per #
 */

/*
https://www.themealdb.com/api.php
https://www.fruityvice.com/
https://github.com/surhud004/Foodish
* */