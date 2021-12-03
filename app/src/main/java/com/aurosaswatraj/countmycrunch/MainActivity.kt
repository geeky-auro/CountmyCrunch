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
                        height_input.text.toString().isNotEmpty() &&
                        age_input.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Male_calculate()
                    }
                        else{
                            it.isEnabled = false
                        }
                }
                "Female"->{
                    if (weight_input.text.toString().isNotEmpty() &&
                        height_input.text.toString().isNotEmpty() &&
                        age_input.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Female_calculate()
                    }
                    else{
                        it.isEnabled = false
                    }
                }
            }

        }
    }

    private fun take_input_BMI_Male_calculate(){
        var wt=weight_input.text.toString().toBigDecimal()
        var ht=height_input.text.toString().toBigDecimal()
        var age=age_input.text.toString().toBigDecimal()

        calculate_BMI_Male(wt,ht,age)
    }
    private fun take_input_BMI_Female_calculate(){
        var wt=weight_input.text.toString().toBigDecimal()
        var ht=height_input.text.toString().toBigDecimal()
        var age=age_input.text.toString().toBigDecimal()

        calculate_BMI_Female(wt,ht,age)
    }

    private fun calculate_BMI_Female(wt: BigDecimal?, ht: BigDecimal?, age: BigDecimal?) {
        var female_weight=wt
        var female_height=ht
        var female_age=age
        TODO("To convert Feet and Inches both in XML and .ktfile")
        TODO("Correct the below line")
        TODO("Refer:https://www.inchcalculator.com/height-converter/")
        TODO("Refer:https://www.baeldung.com/java-separate-double-into-integer-decimal-parts")
        female_height="".toBigDecimal()
        var BMR=66.toBigDecimal() +(13.7.toBigDecimal() * female_weight!!) + (5.toBigDecimal() * female_height!!) - (6.8.toBigDecimal() * female_age!!)
    }

    private fun calculate_BMI_Male(wt:BigDecimal?,ht:BigDecimal?,age:BigDecimal?){
//        For Men
        var male_weight=wt
        var male_height=ht?.times(2.54.toBigDecimal())
        var male_age=age
        var BMR=655.toBigDecimal() +(9.6.toBigDecimal() * male_weight!!) + (1.8.toBigDecimal() * male_height!!) - (4.7.toBigDecimal() * male_age!!)
    }

}

/** Monitor your health in your hands. */
/**
 * Fat Loss = 12-13 calories per pound of body weight
 * Maintenance = 15-16 calories per #
 * Weight Gain = 18-19 calories per #
 */