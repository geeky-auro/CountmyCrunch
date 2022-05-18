package com.aurosaswatraj.countmycrunch



import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aurosaswatraj.countmycrunch.BMIFinder.BMIFinder
import com.aurosaswatraj.countmycrunch.CalorieCounter.CalorieData
import com.aurosaswatraj.countmycrunch.CalorieCounter.CalorieOutputFragment
import com.aurosaswatraj.countmycrunch.CalorieCounter.Calorie_Calculator
import com.aurosaswatraj.countmycrunch.CalorieCounter.FragmentCalorieOutput
import com.aurosaswatraj.countmycrunch.Dialogs.UserManualDialog
import com.aurosaswatraj.countmycrunch.ProgressTracking.MainActivityFragment
import com.aurosaswatraj.countmycrunch.ProgressTracking.Track

import com.thecode.aestheticdialogs.*
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG="MainActivity"
class MainActivity : AppCompatActivity(),FragmentCalorieOutput,Calorie_Calculator.OnSaveClicked{

    private var fragmentB:CalorieOutputFragment?=null
    private var mTwoPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialInitialization(savedInstanceState)




    }



//    TODO:Implement a graph :https://www.mobindustry.net/blog/how-to-quickly-implement-beautiful-charts-in-your-android-app/



    private fun taskEditRequest(task: Track?){

    }






    private fun initialInitialization(savedInstanceState: Bundle?) {
        fragmentB=CalorieOutputFragment()

        UserManualDialog().showDialog(this)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
                BMIFinder()).commit()
        }

        bottomNaviView()
    }

    private fun bottomNaviView(){
        bottomNavigationView.setOnItemSelectedListener {
            val selectedFragment:Fragment?
            when (it.itemId) {
                R.id.BMI1->{selectedFragment=BMIFinder()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
                        selectedFragment
                    ).commit()
                }
                R.id.CALORIE1->{selectedFragment=Calorie_Calculator()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
                        selectedFragment
                    ).commit()
                }
                R.id.TRACKER1->{


//                    cominGsoon()
                selectedFragment=MainActivityFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,selectedFragment)
                        .commit()
             
                }
            }

            true
        }

    }

    private fun cominGsoon(){
        AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.WARNING)
            .setTitle("Coming Soon")
            .setMessage("Work in Progress!")
            .setCancelable(true)
            .setDarkMode(true)
            .setGravity(Gravity.CENTER)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                    //actions...
                }
            })
            .show()
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.buttom_nev_menu, menu)
        return true
    }

    override fun onOutputSent(data: ArrayList<CalorieData>) {

        Log.d(TAG,"Data in MainActivity is $data")
        fragmentB?.updateEditText(data)
    }

    override fun onSaveClicked() {

    }


}

/** Monitor your health in your hands. */
/**
 * Fat Loss = 12-13 calories per pound of body weight
 * Maintenance = 15-16 calories per #
 * Weight Gain = 18-19 calories per #
 */

//Pass Data between Fragments; https://www.youtube.com/watch?v=i22INe14JUc&ab_channel=CodinginFlow


/*
https://www.themealdb.com/api.php
https://www.fruityvice.com/
https://github.com/surhud004/Foodish
* */