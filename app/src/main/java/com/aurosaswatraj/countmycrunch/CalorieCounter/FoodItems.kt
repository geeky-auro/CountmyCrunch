package com.aurosaswatraj.countmycrunch.CalorieCounter

class FoodItems(val image:Int, val name:String,val calorie:Double) {

    fun getMimgae(): Int {
        return image
    }

    fun getMtext():String{
        return name
    }
    fun getMCalorie():Double{
        return calorie
    }

}