package com.aurosaswatraj.countmycrunch.BMIFinder

import android.animation.ObjectAnimator
import android.app.Application
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
private const val TAG="BMIViewModel"
class BMIViewModel : ViewModel() {
//    Refer this: https://stackoverflow.com/questions/55914752/when-to-use-mutablelivedata-and-livedata


     fun calculate_BMI_Female(wt: BigDecimal?, ht_ft: BigDecimal?, ht_in: BigDecimal?, age: BigDecimal?) {
        val female_weight=wt
        val female_height_ft=ht_ft
        val female_height_in=ht_in
        var heightinmeter= female_height_ft?.times(12.0.toBigDecimal())?.plus(female_height_in!!)
        heightinmeter=heightinmeter?.times(2.54.toBigDecimal())
        heightinmeter=heightinmeter?.div(100.0.toBigDecimal())
        heightinmeter=heightinmeter?.times(heightinmeter)

        val BMI=female_weight?.div(heightinmeter!!)
        Log.d(TAG,"BMI calculated is $BMI")
        showRecommendations(BMI)
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
         Log.d(TAG,"Height calculated is $heightinmeter")
        val BMI=male_weight?.div(heightinmeter!!)
//        val BMI=655.toBigDecimal() +(9.6.toBigDecimal() * male_weight!!) + (1.8.toBigDecimal() * heightinmeter!!) - (4.7.toBigDecimal() * male_age!!)
        Log.d(TAG,"BMI calculated is $BMI")
        showRecommendations(BMI)

//        Display Categories
        /** Underweight = <18.5
        Normal weight = 18.5–24.9
        Overweight = 25–29.9
        Obesity = BMI of 30 or greater */
    }

     fun showRecommendations(BMI: BigDecimal?){
         var BMIResult=""
        if (BMI!!<=18.5.toBigDecimal()){
            BMIResult="Underweight"
            Log.d(TAG,"Underweight")
        }else if(BMI >18.5.toBigDecimal() && BMI <=24.9.toBigDecimal()){
            BMIResult="Normal weight"
            Log.d(TAG,"Normal weight")
        }else if(BMI>24.9.toBigDecimal() && BMI<=29.9.toBigDecimal()){
            BMIResult="OverWeight"
            Log.d(TAG,"OverWeight")
        }else{
            BMIResult="Obesity"
            Log.d(TAG,"Obesity")
        }

         val HeadTitle="BMI table for adults\n"
         val permanentMsg="Severe Thinness for BMI range < 16\n" +
                 "Moderate Thinness for BMI range 16 - 17\n" +
                 "Mild Thinness for BMI range 17 - 18.5\n" +
                 "Normal for BMI range 18.5 - 25\n" +
                 "Overweight for BMI range 25-30\n" +
                 "Obese Class I for BMI range 30-35\n" +
                 "Obese Class II for BMI range 35-40\n" +
                 "Obese Class III > for BMI range 40\n"

         Log.d(TAG,BMIResult+"\n"+HeadTitle+permanentMsg)

    }

    //    Enhancements

     fun goback(view: View?, gender:String?) {
        when(gender){
            "male"->{ val animY = ObjectAnimator.ofFloat(view, "translationX", 50f, 0f)
                animY.duration = 500 //1sec
                animY.interpolator = BounceInterpolator()
                animY.repeatCount = 0
                animY.start() }
            "female"->{
                val animY = ObjectAnimator.ofFloat(view, "translationX", -50f, 0f)
                animY.duration = 500 //1sec
                animY.interpolator = BounceInterpolator()
                animY.repeatCount = 0
                animY.start()
            }
        }


    }


}