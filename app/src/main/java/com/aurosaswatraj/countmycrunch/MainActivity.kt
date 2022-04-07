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
        genderSelection()
        initializeitems()
        recyclerview.layoutManager=LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL,false)
        recyclerview.adapter=FoodAdapter(food,this)
        selectAMR()

        submit_button.setOnClickListener {
            Log.d(TAG,"Total Calorie Consumed as a whole is ${totalCalorieConsumed()}")

//            https://github.com/PatilShreyas/MaterialDialog-Android
            when(gender){
                "male"->{
                    if (weight_inputi.text.toString().isNotEmpty() &&
                        heightin_inputi.text.toString().isNotEmpty() &&
                        age_inputi.text.toString().isNotEmpty() &&
                        heightft_inputi.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Male_calculate()
                    }
                    else{

//                        TODO:Alternative can be shown is a error message
                    }
                }
                "female"->{
                    if (weight_inputi.text.toString().isNotEmpty() &&
                        heightin_inputi.text.toString().isNotEmpty() &&
                        age_inputi.text.toString().isNotEmpty() &&
                        heightft_inputi.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Female_calculate()
                    }
                    else{

//                        TODO:Alternative can be shown is a error message
                    }
                }
            }
        }


    }

    private fun take_input_BMI_Male_calculate(){
        val wt=weight_input.text.toString().toBigDecimal()
        val ht_ft=heightft_input.text.toString().toBigDecimal()
        val ht_in=heightin_input.text.toString().toBigDecimal()
        val age=age_input.text.toString().toBigDecimal()

        calculate_BMR_Male(wt,ht_ft,ht_in,age)
    }
    private fun take_input_BMI_Female_calculate(){
        val wt=weight_input.text.toString().toBigDecimal()
        val ht_ft=heightft_input.text.toString().toBigDecimal()
        val ht_in=heightin_input.text.toString().toBigDecimal()
        val age=age_input.text.toString().toBigDecimal()

        calculate_BMR_Female(wt,ht_ft,ht_in,age)
    }

    fun calculate_BMR_Female(wt: BigDecimal?, ht_ft: BigDecimal?, ht_in: BigDecimal?, age: BigDecimal?) {
        val female_age=age
        val female_weight=wt
        val female_height_ft=ht_ft
        val female_height_in=ht_in
        val heightincm= female_height_ft?.times(30.48.toBigDecimal())?.plus(female_height_in!!.times(2.54.toBigDecimal()))

        var BMR: BigDecimal? =((female_weight?.times(9.563.toBigDecimal()))?.plus(heightincm!!.times(1.850.toBigDecimal()))?.subtract(female_age?.times(4.676.toBigDecimal())))
        BMR=BMR?.plus(655.1.toBigDecimal())

        calculate_AMR(BMR)


    }

    fun calculate_BMR_Male(wt: BigDecimal?, ht_ft: BigDecimal?, ht_in: BigDecimal?, age: BigDecimal?){
//        For Men
        val male_weight=wt
        val male_height_ft=ht_ft
        val male_height_in=ht_in
        val male_age=age
        val heightincm= male_height_ft?.times(30.48.toBigDecimal())?.plus(male_height_in!!.times(2.54.toBigDecimal()))

        var BMR: BigDecimal? =((male_weight?.times(13.75.toBigDecimal()))?.plus(heightincm!!.times(5.003.toBigDecimal()))?.subtract(male_age?.times(6.755.toBigDecimal())))
        BMR=BMR?.plus(66.47.toBigDecimal())


        calculate_AMR(BMR)


    }

    private fun calculate_AMR(bmr: BigDecimal?) {
        val Sedentary=bmr?.multiply(1.2.toBigDecimal())
        val Lightly_active=bmr?.multiply(1.375.toBigDecimal())
        val Moderately_active=bmr?.multiply(1.55.toBigDecimal())
        val Active =bmr?.multiply(1.725.toBigDecimal())
        val Very_active =bmr?.multiply(1.9.toBigDecimal())

        Log.d(TAG,"Sedentary =$Sedentary")
        Log.d(TAG,"Lightly active =$Lightly_active")
        Log.d(TAG,"Moderately_active =$Moderately_active")
        Log.d(TAG,"Active =$Active")
        Log.d(TAG,"Very active =$Very_active")
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