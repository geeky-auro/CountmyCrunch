package com.aurosaswatraj.countmycrunch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.buttom_nev_menu, menu)

        
        return true
    }

    private var gender="Male"

    private fun submit_Button(){
        var listener=View.OnClickListener {

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
    }

    private fun take_input_BMI_Male_calculate(){
        var wt=weight_input.text.toString().toBigDecimal()
        var ht_ft=heightft_input.text.toString().toBigDecimal()
        var ht_in=heightin_input.text.toString().toBigDecimal()
        var age=age_input.text.toString().toBigDecimal()

        calculate_BMI_Male(wt,ht_ft,ht_in,age)
    }
    private fun take_input_BMI_Female_calculate(){
        var wt=weight_input.text.toString().toBigDecimal()
        var ht_ft=heightft_input.text.toString().toBigDecimal()
        var ht_in=heightin_input.text.toString().toBigDecimal()
        var age=age_input.text.toString().toBigDecimal()

        calculate_BMI_Female(wt,ht_ft,ht_in,age)
    }

    private fun calculate_BMI_Female(wt: BigDecimal?, ht_ft:BigDecimal?,ht_in:BigDecimal?, age: BigDecimal?) {
        var female_weight=wt
        var female_height_ft=ht_ft
        var female_height_in=ht_in
        var female_age=age

        var heightincm= female_height_ft?.times(12.0.toBigDecimal())?.plus(female_height_in!!)
        heightincm=heightincm?.times(2.54.toBigDecimal())

        var female_height="".toBigDecimal()
        var BMR=66.toBigDecimal() +(13.7.toBigDecimal() * female_weight!!) + (5.toBigDecimal() * heightincm!!) - (6.8.toBigDecimal() * female_age!!)
    }

    private fun calculate_BMI_Male(wt: BigDecimal?, ht_ft:BigDecimal?,ht_in:BigDecimal?, age: BigDecimal?){
//        For Men
        var male_weight=wt
        var male_height_ft=ht_ft
        var male_height_in=ht_in
        var male_age=age
        var heightincm= male_height_ft?.times(12.0.toBigDecimal())?.plus(male_height_in!!)
        heightincm=heightincm?.times(2.54.toBigDecimal())
        val BMR=655.toBigDecimal() +(9.6.toBigDecimal() * male_weight!!) + (1.8.toBigDecimal() * heightincm!!) - (4.7.toBigDecimal() * male_age!!)
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