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
class MainActivity : AppCompatActivity(), SelectListener {

    var food:ArrayList<FoodItems> =ArrayList()
//    To select AMR from the dropdown
//    TODO Variable to be cleared after Reset with the dropdown value
    private var selecteditem=""
    private var totCalorie=0.0
    private var gender="male"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calorie_counter_u_i)
        initializeitems()
        recyclerview.layoutManager=LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL,false)
        recyclerview.adapter=FoodAdapter(food,this)
        selectAMR()

        submit_button.setOnClickListener {
            Log.d(TAG,"Total Calorie Consumed as a whole is ${totalCalorieConsumed()}")
//       https://stackoverflow.com/questions/43088902/how-to-get-sum-of-integer-value-from-recyclerview
        }




    }

    fun calculate_BMI_Female(wt: BigDecimal?, ht_ft: BigDecimal?, ht_in: BigDecimal?, age: BigDecimal?) {
        val female_weight=wt
        val female_height_ft=ht_ft
        val female_height_in=ht_in
        var heightinmeter= female_height_ft?.times(12.0.toBigDecimal())?.plus(female_height_in!!)
        heightinmeter=heightinmeter?.times(2.54.toBigDecimal())
        heightinmeter=heightinmeter?.div(100.0.toBigDecimal())
        heightinmeter=heightinmeter?.times(heightinmeter)

        val BMI=female_weight?.div(heightinmeter!!)

//        showRecommendations(BMI)
    }

    fun calculate_BMI_Male(wt: BigDecimal?, ht_ft: BigDecimal?, ht_in: BigDecimal?, age: BigDecimal?){
//        For Men
        val male_weight=wt
        val male_height_ft=ht_ft
        val male_height_in=ht_in
        var male_age=age
//        male_weight=male_weight?.times(2.20462.toBigDecimal())
        var heightinmeter= male_height_ft?.times(12.0.toBigDecimal())?.plus(male_height_in!!)
        heightinmeter=heightinmeter?.times(2.54.toBigDecimal())
        heightinmeter=heightinmeter?.div(100.0.toBigDecimal())
        heightinmeter=heightinmeter?.times(heightinmeter)
        val BMI=male_weight?.div(heightinmeter!!)
//        val BMI=655.toBigDecimal() +(9.6.toBigDecimal() * male_weight!!) + (1.8.toBigDecimal() * heightinmeter!!) - (4.7.toBigDecimal() * male_age!!)

//        showRecommendations(BMI)

//        Display Categories
        /** Underweight = <18.5
        Normal weight = 18.5–24.9
        Overweight = 25–29.9
        Obesity = BMI of 30 or greater */
    }



    private fun genderSelection(){
        btn_boy.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_girl.setBackgroundColor(Color.parseColor("#9E7777"))
            gender="male"

//            viewModel?.goback(it,gender)
        }

        btn_girl.setOnClickListener{
            it.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_boy.setBackgroundColor(Color.parseColor("#9E7777"))
            gender="female"
//            viewModel?.goback(it,gender)
        }

    }



    fun goback(view: View?, gender:String?) {
        when (gender) {
            "male" -> {
                val animY = ObjectAnimator.ofFloat(view, "translationX", 50f, 0f)
                animY.duration = 500 //1sec
                animY.interpolator = BounceInterpolator()
                animY.repeatCount = 0
                animY.start()
            }
            "female" -> {
                val animY = ObjectAnimator.ofFloat(view, "translationX", -50f, 0f)
                animY.duration = 500 //1sec
                animY.interpolator = BounceInterpolator()
                animY.repeatCount = 0
                animY.start()
            }
        }
    }


    private fun totalCalorieConsumed():Double{
       var counter=0
        food.forEach {
            totCalorie += food[counter].getMnoOfItems() * food[counter].getMCalorie()
            counter++

        }
        return totCalorie
    }

    private fun selectAMR(){
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
            Log.d(TAG,"AMR selected is $selecteditem")
        }
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)

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