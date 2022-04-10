package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import java.math.BigDecimal

private const val TAG="CalorieCounterVM"

class CalorieCounterViewModel: ViewModel() {

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

    fun genderAnim(view: View?, gender:String?) {
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


    fun initializeitems(food:ArrayList<FoodItems>){
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



}