package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.calorie_counter_u_i.*
import java.math.BigDecimal


private const val TAG="Calorie_Calculator"


/**
 * A simple [Fragment] subclass.
 * Use the [Calorie_Calculator.newInstance] factory method to
 * create an instance of this fragment.
 */
class Calorie_Calculator : Fragment(), SelectListener,AdapterView.OnItemSelectedListener   {


    var ActivityLevel = arrayOf("Sedentary(little or no exercise)", "Light(exercise 1–3 times/week)", "Moderate(exercise 4–5 times/week)", "Active(intense exercise 6–7 times/week)")

    var food:ArrayList<FoodItems> =ArrayList()
    //    To select AMR from the dropdown
    //    TODO Variable to be cleared after Reset with the dropdown value
    private var selecteditem=""
    private var totCalorie=0.0
    private var gender="male"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genderSelection()
        initializeitems()
        recyclerview.layoutManager=
            LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        recyclerview.adapter=FoodAdapter(food,this)



        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.

        Spinner.setOnItemSelectedListener(this)

        //Creating the ArrayAdapter instance having the ActivityLevel list
        //Creating the ArrayAdapter instance having the ActivityLevel list
        val adapter = activity?.let {
            ArrayAdapter<String>(
                it,
                R.layout.dropdown_item,
                ActivityLevel
            )
        }

        adapter?.setDropDownViewResource(R.layout.dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        //Setting the ArrayAdapter data on the Spinner
        Spinner.adapter = adapter


        submit_button.setOnClickListener {
            Log.d(TAG,"Total Calorie Consumed as a whole is ${totalCalorieConsumed()}")

            val wt=weight_inputi.text.toString().toBigDecimal()
            val ht_ft=heightft_inputi.text.toString().toBigDecimal()
            val ht_in=heightin_inputi.text.toString().toBigDecimal()
            val age=age_inputi.text.toString().toBigDecimal()
//            https://github.com/PatilShreyas/MaterialDialog-Android
            when(gender){
                "male"->{
                    if (weight_inputi.text.toString().isNotEmpty() &&
                        heightin_inputi.text.toString().isNotEmpty() &&
                        age_inputi.text.toString().isNotEmpty() &&
                        heightft_inputi.text.toString().isNotEmpty())
                    {

                        take_input_BMI_Male_calculate(wt,ht_ft,ht_in,age)
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
                        take_input_BMI_Female_calculate(wt,ht_ft,ht_in,age)
                    }
                    else{

//                        TODO:Alternative can be shown is a error message
                    }
                }
            }
        }



    }

    private fun take_input_BMI_Male_calculate(wt: BigDecimal,ht_ft: BigDecimal,ht_in: BigDecimal,age: BigDecimal) {


        calculate_BMR_Male(wt,ht_ft,ht_in,age)
    }
    private fun take_input_BMI_Female_calculate( wt: BigDecimal,ht_ft: BigDecimal,ht_in: BigDecimal,age: BigDecimal){


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
            genderAnim(it,gender)
//            viewModel?.genderAnim(it,gender)
        }

        btn_girl.setOnClickListener{
            it.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_boy.setBackgroundColor(Color.parseColor("#9E7777"))
            gender="female"
            genderAnim(it,gender)
//            viewModel?.genderAnim(it,gender)
        }

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


    private fun totalCalorieConsumed():Double{
        var counter=0
        food.forEach {
            totCalorie += food[counter].getMnoOfItems() * food[counter].getMCalorie()
            counter++

        }
        return totCalorie
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calorie_counter_u_i, container, false)
    }



    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
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

    override fun onNothingSelected(p0: AdapterView<*>?) {
       Log.d(TAG,"Sit Silently")
    }


}






