package com.aurosaswatraj.countmycrunch

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.calorie_counter_u_i.*


private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calorie_counter_u_i)

//        https://www.geeksforgeeks.org/exposed-drop-down-menu-in-android/




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