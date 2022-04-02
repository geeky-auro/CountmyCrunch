package com.aurosaswatraj.countmycrunch

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.CalorieCounter.FoodAdapter
import com.aurosaswatraj.countmycrunch.CalorieCounter.FoodItems

import kotlinx.android.synthetic.main.calorie_counter_u_i.*


private const val TAG="MainActivity"
class MainActivity : AppCompatActivity(),FoodAdapter.onTaskClickListener {

    var food:ArrayList<FoodItems> =ArrayList()
    var selecteditem=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calorie_counter_u_i)
        initializeitems()
        recyclerview.layoutManager=LinearLayoutManager(applicationContext,RecyclerView.HORIZONTAL,false)
        recyclerview.adapter=FoodAdapter(food,applicationContext,this)
//        https://stackoverflow.com/questions/28296708/get-clicked-item-and-its-position-in-recyclerview?rq=1




//        https://www.geeksforgeeks.org/exposed-drop-down-menu-in-android/

        // get reference to the string array that we just created
        val activityLevel = resources.getStringArray(R.array.Activity_Level)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, activityLevel)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setOnItemClickListener { parent, view, position, id ->
            Log.d(TAG,"$position selected")
            selecteditem = when(position){
                0->{
                    "Sedentary"
                }
                1->{
                    "Lightly"
                }
                2->{
                    "Moderate"
                }
                3->{
                    "Active"
                }
                else->{
                    "Moderate"
                }
            }
        }
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)

        //onClickListener....>!












        submit_button.setOnClickListener {

//       https://stackoverflow.com/questions/43088902/how-to-get-sum-of-integer-value-from-recyclerview
        }




    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.buttom_nev_menu, menu)
        return true
    }

    private fun initializeitems(){
        food.add(FoodItems(R.drawable.apple,"Apple",120.0))
        food.add(FoodItems(R.drawable.apple_cider,"Apple Cider",1221.0))
        food.add(FoodItems(R.drawable.asparagus,"Asparagus",122.5))
        food.add(FoodItems(R.drawable.bananas,"Banana",120.0))
        food.add(FoodItems(R.drawable.beef,"Beef",120.0))
        food.add(FoodItems(R.drawable.beer,"Beer",120.0))
        food.add(FoodItems(R.drawable.white_bread,"White Bread",120.0))
        food.add(FoodItems(R.drawable.broccoli,"Broccoli",120.0))
        food.add(FoodItems(R.drawable.butter,"Butter",120.0))
        food.add(FoodItems(R.drawable.caesar_salad,"Caesar Salad",120.0))
        food.add(FoodItems(R.drawable.carrot,"Carrots",120.0))
        food.add(FoodItems(R.drawable.cheeseburger,"Cheese Burger",120.0))
        food.add(FoodItems(R.drawable.cooked_chicken,"Chicken Cooked",120.0))
        food.add(FoodItems(R.drawable.coca_cola,"Coca-Cola",120.0))
        food.add(FoodItems(R.drawable.corn,"Corn",120.0))
        food.add(FoodItems(R.drawable.cucumber,"Cucumber",120.0))
        food.add(FoodItems(R.drawable.dark_chocolate,"Dark Chocolate",120.0))
        food.add(FoodItems(R.drawable.diet_coke,"Diet Coke",120.0))
        food.add(FoodItems(R.drawable.egg,"Egg",120.0))
        food.add(FoodItems(R.drawable.fish,"Fish",136.0))
        food.add(FoodItems(R.drawable.catfish,"CatFish",136.0))
        food.add(FoodItems(R.drawable.grapes,"Grapes",120.0))
        food.add(FoodItems(R.drawable.hamburger,"Hamburger",120.0))
        food.add(FoodItems(R.drawable.lettuce,"Lettuce",120.0))
        food.add(FoodItems(R.drawable.milk,"Milk",120.0))
        food.add(FoodItems(R.drawable.orange,"Orange",120.0))
        food.add(FoodItems(R.drawable.orange_juice,"Orange Juice",120.0))
        food.add(FoodItems(R.drawable.peach,"Peach",120.0))
        food.add(FoodItems(R.drawable.pear,"Pear",120.0))
        food.add(FoodItems(R.drawable.pineapple,"Pineapple",120.0))
        food.add(FoodItems(R.drawable.pizza,"Pizza",120.0))
        food.add(FoodItems(R.drawable.pork,"Pork",120.0))
        food.add(FoodItems(R.drawable.potato,"Potato",120.0))
        food.add(FoodItems(R.drawable.rice,"Rice",120.0))
        food.add(FoodItems(R.drawable.sandwich,"Sandwich",120.0))
        food.add(FoodItems(R.drawable.shrimp,"Shrimp,Cooked",120.0))
        food.add(FoodItems(R.drawable.strawberry,"Strawberry",120.0))
        food.add(FoodItems(R.drawable.tofu,"Tofu",120.0))
        food.add(FoodItems(R.drawable.tomato,"Tomato",120.0))
        food.add(FoodItems(R.drawable.watermelon,"Watermelon",120.0))
        food.add(FoodItems(R.drawable.yoghurt_low_fat,"Yogurt (Low-fat)",120.0))
        food.add(FoodItems(R.drawable.yoghurt_non_fat,"Yogurt (non-fat)",120.0))
    }

    override fun recyclerViewListClicked(v: View?, position: Int) {
        TODO("Not yet implemented")
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