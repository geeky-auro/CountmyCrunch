package com.aurosaswatraj.countmycrunch

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.CalorieCounter.FoodAdapter
import com.aurosaswatraj.countmycrunch.CalorieCounter.FoodItems

import kotlinx.android.synthetic.main.calorie_counter_u_i.*


private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {

    var food:ArrayList<FoodItems> =ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calorie_counter_u_i)
        initializeitems()
        recyclerview.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        recyclerview.adapter=FoodAdapter(food)

//        https://www.geeksforgeeks.org/exposed-drop-down-menu-in-android/


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.buttom_nev_menu, menu)
        return true
    }

    private fun initializeitems(){
        food.add(FoodItems(R.drawable.ic_launcher_background,"Background"))
        food.add(FoodItems(R.drawable.apple,"Background"))
        food.add(FoodItems(R.drawable.ic_launcher_background,"Background"))
        food.add(FoodItems(R.drawable.ic_launcher_background,"Background"))
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