package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.fragment_calorie_output.*


/**
 * A simple [Fragment] subclass.
 * Use the [CalorieOutputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val TAG="CalorieOutputFragment"
class CalorieOutputFragment : Fragment(R.layout.fragment_calorie_output) {


    private var listData=ArrayList<CalorieData>()
//    private var adapter:ViewPagerAdapter?=null

    fun updateEditText(data: ArrayList<CalorieData>) {
        Log.d(TAG,"In OutputFragment $data")
        listData=data
//        adapter=ViewPagerAdapter(data)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onCreateView Started")
        Log.d(TAG,"onCreateView List Data:$listData")
        val adapter=ViewPagerAdapter(listData)
        viewPager.adapter=adapter

    }






}