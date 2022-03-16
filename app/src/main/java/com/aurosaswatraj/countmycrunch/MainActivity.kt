package com.aurosaswatraj.countmycrunch

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.CalorieCounter.FoodRecyclerViewAdapter
import kotlinx.android.synthetic.main.calorie_counter_u_i.*


private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {

    // Recycler View object
    var recyclerView: RecyclerView? = null

    // Array list for recycler view data source
    var source: ArrayList<String>? = null

    // Layout Manager
    var RecyclerViewLayoutManager: RecyclerView.LayoutManager? = null

    // adapter class object
    var adapter: FoodRecyclerViewAdapter? = null

    // Linear Layout Manager
    var HorizontalLayout: LinearLayoutManager? = null

    var ChildView: View? = null
    var RecyclerViewItemPosition = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calorie_counter_u_i)
        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        AddItemsToRecyclerViewArrayList()
        adapter=FoodRecyclerViewAdapter(source)
        recyclerView?.adapter=adapter
        recyclerView?.layoutManager=RecyclerViewLayoutManager

        HorizontalLayout = LinearLayoutManager(
            this,LinearLayoutManager.HORIZONTAL,
            false)
        recyclerView?.layoutManager = HorizontalLayout

        // Set adapter on recycler view
        recyclerView?.adapter = adapter

//        https://www.geeksforgeeks.org/exposed-drop-down-menu-in-android/
//        https://www.geeksforgeeks.org/android-horizontal-recyclerview-with-examples/



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.buttom_nev_menu, menu)
        return true
    }

    fun AddItemsToRecyclerViewArrayList() {
        // Adding items to ArrayList
        source = ArrayList()
        source!!.add("gfg")
        source!!.add("is")
        source!!.add("best")
        source!!.add("site")
        source!!.add("for")
        source!!.add("interview")
        source!!.add("preparation")
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