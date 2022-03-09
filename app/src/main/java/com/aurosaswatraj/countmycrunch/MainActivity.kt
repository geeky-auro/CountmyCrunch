package com.aurosaswatraj.countmycrunch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submit_Button()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.buttom_nev_menu, menu)

        
        return true
    }

    private var gender="Male"
//    TODO:Create a menu for Male and Female selection with color changes

    private fun submit_Button(){

        val listener=View.OnClickListener {

            when(gender){
                "Male"->{
                        if (weight_input.text.toString().isNotEmpty() &&
                            heightin_input.text.toString().isNotEmpty() &&
                            age_input.text.toString().isNotEmpty() &&
                            heightft_input.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Male_calculate()
                    }
                        else{
                            it.isEnabled = false
//                        Alternative can be shown is a error message
                        }
                }
                "Female"->{
                    if (weight_input.text.toString().isNotEmpty() &&
                        heightin_input.text.toString().isNotEmpty() &&
                        age_input.text.toString().isNotEmpty() &&
                        heightft_input.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Female_calculate()
                    }
                    else{
                        it.isEnabled = false
//                        Alternative can be shown is a error message
                    }
                }
            }

        }
        submit_button.setOnClickListener(listener)
    }

    private fun take_input_BMI_Male_calculate(){
        val wt=weight_input.text.toString().toBigDecimal()
        val ht_ft=heightft_input.text.toString().toBigDecimal()
        val ht_in=heightin_input.text.toString().toBigDecimal()
        val age=age_input.text.toString().toBigDecimal()

        calculate_BMI_Male(wt,ht_ft,ht_in,age)
    }
    private fun take_input_BMI_Female_calculate(){
        val wt=weight_input.text.toString().toBigDecimal()
        val ht_ft=heightft_input.text.toString().toBigDecimal()
        val ht_in=heightin_input.text.toString().toBigDecimal()
        val age=age_input.text.toString().toBigDecimal()

        calculate_BMI_Female(wt,ht_ft,ht_in,age)
    }

    private fun calculate_BMI_Female(wt: BigDecimal?, ht_ft:BigDecimal?,ht_in:BigDecimal?, age: BigDecimal?) {
        val female_weight=wt
        val female_height_ft=ht_ft
        val female_height_in=ht_in


        var heightinmeter= female_height_ft?.times(12.0.toBigDecimal())?.plus(female_height_in!!)
        heightinmeter=heightinmeter?.times(2.54.toBigDecimal())
        heightinmeter=heightinmeter?.div(100.0.toBigDecimal())
        heightinmeter=heightinmeter?.times(heightinmeter)

        val BMI=female_weight?.div(heightinmeter!!)
        Log.d(TAG,"BMI calculated is $BMI")
    }

    private fun calculate_BMI_Male(wt: BigDecimal?, ht_ft:BigDecimal?,ht_in:BigDecimal?, age: BigDecimal?){
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
        Log.d(TAG,"BMI calculated is $BMI")

//        Display Categories
/** Underweight = <18.5
Normal weight = 18.5–24.9
Overweight = 25–29.9
Obesity = BMI of 30 or greater */
    }

    private fun showRecommendations(BMI:BigDecimal?){
        if (BMI!!<=18.5.toBigDecimal()){
        Log.d(TAG,"Underweight")
        }else if(BMI >18.5.toBigDecimal() && BMI <=24.9.toBigDecimal()){
        Log.d(TAG,"Normal weight")
        }else if(BMI>24.9.toBigDecimal() && BMI<=29.9.toBigDecimal()){
            Log.d(TAG,"OverWeight")
        }else{
            Log.d(TAG,"Obesity")
        }

}
}

/** Monitor your health in your hands. */
/**
 * Fat Loss = 12-13 calories per pound of body weight
 * Maintenance = 15-16 calories per #
 * Weight Gain = 18-19 calories per #
 */

/* https://www.fruityvice.com/
https://github.com/surhud004/Foodish
* */