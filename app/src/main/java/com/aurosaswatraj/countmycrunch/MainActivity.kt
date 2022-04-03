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
import com.aurosaswatraj.countmycrunch.CalorieCounter.SelectListener

import kotlinx.android.synthetic.main.calorie_counter_u_i.*


private const val TAG="MainActivity"
class MainActivity : AppCompatActivity(), SelectListener {

    var food:ArrayList<FoodItems> =ArrayList()
    var selecteditem=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calorie_counter_u_i)
        initializeitems()
        recyclerview.layoutManager=LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL,false)
        recyclerview.adapter=FoodAdapter(food,this)
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
        food.add(FoodItems(R.drawable.apple,"Apple",59.0,0))
        food.add(FoodItems(R.drawable.apple_cider,"Apple Cider",117.0,0))
        food.add(FoodItems(R.drawable.asparagus,"Asparagus",27.5,0))
        food.add(FoodItems(R.drawable.bananas,"Banana",151.0,0))
        food.add(FoodItems(R.drawable.beef,"Beef",142.0,0))
        food.add(FoodItems(R.drawable.beer,"Beer",154.0,0))
        food.add(FoodItems(R.drawable.white_bread,"White Bread",75.0,0))
        food.add(FoodItems(R.drawable.broccoli,"Broccoli",45.0,0))
        food.add(FoodItems(R.drawable.butter,"Butter",102.0,0))
        food.add(FoodItems(R.drawable.caesar_salad,"Caesar Salad",481.0,0))
        food.add(FoodItems(R.drawable.carrot,"Carrots",50.0,0))
        food.add(FoodItems(R.drawable.cheeseburger,"Cheese Burger",285.0,0))
        food.add(FoodItems(R.drawable.cooked_chicken,"Chicken Cooked",136.0,0))
        food.add(FoodItems(R.drawable.coca_cola,"Coca-Cola",150.0,0))
        food.add(FoodItems(R.drawable.corn,"Corn",132.0,0))
        food.add(FoodItems(R.drawable.cucumber,"Cucumber",17.0,0))
        food.add(FoodItems(R.drawable.dark_chocolate,"Dark Chocolate",155.0,0))
        food.add(FoodItems(R.drawable.diet_coke,"Diet Coke",0.0,0))
        food.add(FoodItems(R.drawable.egg,"Egg",78.0,0))
        food.add(FoodItems(R.drawable.fish,"Fish",35.0,0))
        food.add(FoodItems(R.drawable.catfish,"CatFish",136.0,0))
        food.add(FoodItems(R.drawable.grapes,"Grapes",100.0,0))
        food.add(FoodItems(R.drawable.hamburger,"Hamburger",250.0,0))
        food.add(FoodItems(R.drawable.lettuce,"Lettuce",5.0,0))
        food.add(FoodItems(R.drawable.milk,"Milk",102.0,0))
        food.add(FoodItems(R.drawable.orange,"Orange",53.0,0))
        food.add(FoodItems(R.drawable.orange_juice,"Orange Juice",111.0,0))
        food.add(FoodItems(R.drawable.peach,"Peach",67.0,0))
        food.add(FoodItems(R.drawable.pear,"Pear",82.0,0))
        food.add(FoodItems(R.drawable.pineapple,"Pineapple",82.0,0))
        food.add(FoodItems(R.drawable.pizza,"Pizza",285.0,0))
        food.add(FoodItems(R.drawable.pork,"Pork",137.0,0))
        food.add(FoodItems(R.drawable.potato,"Potato",130.0,0))
        food.add(FoodItems(R.drawable.rice,"Rice",206.0,0))
        food.add(FoodItems(R.drawable.sandwich,"Sandwich",200.0,0))
        food.add(FoodItems(R.drawable.shrimp,"Shrimp,Cooked",56.0,0))
        food.add(FoodItems(R.drawable.strawberry,"Strawberry",53.0,0))
        food.add(FoodItems(R.drawable.tofu,"Tofu",86.0,0))
        food.add(FoodItems(R.drawable.tomato,"Tomato",22.0,0))
        food.add(FoodItems(R.drawable.watermelon,"Watermelon",50.0,0))
        food.add(FoodItems(R.drawable.yoghurt_low_fat,"Yogurt (Low-fat)",154.0,0))
        food.add(FoodItems(R.drawable.yoghurt_non_fat,"Yogurt (non-fat)",110.0,0))
    }

    override fun onAddItemClicked(foodItems: FoodItems?, position: Int) {
        Log.d("MainActivity","RecycleritemCliced sliced no of item included is ${food[position].noOfItems++}")
        Log.d(TAG,"No of Items consisting is ${food[position].getMnoOfItems()}")
        //food.add(position, FoodItems(food[position].getMimgae(),food[position].getMtext(),food[position].getMCalorie(),food[position].noOfItems++))
        recyclerview.adapter?.notifyItemChanged(position)
    }

    override fun onSubItemClicked(foodItems: FoodItems?, position: Int) {
        Log.d("MainActivity","RecycleritemCliced sliced no of item included is ${food[position].noOfItems--}")
        //food.add(position, FoodItems(food[position].getMimgae(),food[position].getMtext(),food[position].getMCalorie(),food[position].noOfItems++))
        Log.d(TAG,"No of Items consisting is ${food[position].getMnoOfItems()}")
        recyclerview.adapter?.notifyItemChanged(position)
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