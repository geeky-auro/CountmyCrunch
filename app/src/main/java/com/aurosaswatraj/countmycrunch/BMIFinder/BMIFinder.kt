package com.aurosaswatraj.countmycrunch.BMIFinder

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aurosaswatraj.countmycrunch.Dialogs.ErrorDialogs
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.fragment_b_m_i_finder.*


private const val TAG = "BMIFinder"


/**
 * A simple [Fragment] subclass.
 * Use the [BMIFinder.newInstance] factory method to
 * create an instance of this fragment.
 */
class BMIFinder : Fragment(R.layout.fragment_b_m_i_finder) {

    var viewModel:BMIViewModel?=null
    private var gender="male"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit_Button()
        genderSelection()
    }

    private fun genderSelection(){
        btn_boy.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_girl.setBackgroundColor(Color.parseColor("#9E7777"))
            gender="male"
            Log.d(TAG, gender)
            viewModel?.goback(it,gender)
        }

        btn_girl.setOnClickListener{
            it.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_boy.setBackgroundColor(Color.parseColor("#9E7777"))
            gender="female"
            Log.d(TAG, gender)
            viewModel?.goback(it,gender)
        }

    }

    private fun submit_Button(){

        val listener=View.OnClickListener {

            when(gender){
                "male"->{
                    if (weight_input.text.toString().isNotEmpty() &&
                        heightin_input.text.toString().isNotEmpty() &&
                        age_input.text.toString().isNotEmpty() &&
                        heightft_input.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Male_calculate()
                    }
                    else{
                    var msg=""
                    val dialog=ErrorDialogs(requireContext())
                     if (weight_input.text.toString().isEmpty()){
                         msg="\nPlease provide positive weight value."
                     }
                        if (heightin_input.text.toString().isEmpty()){
                            msg=msg.plus("\nPlease provide positive height in inches value.")
                        }

                        if (age_input.text.toString().isEmpty()){
                            msg=msg.plus("\nPlease provide an age between 5 and 100.")
                        }

                        if (heightft_input.text.toString().isEmpty()){
                            msg=msg.plus("\nPlease provide positive height in Foot value.")
                        }
                        dialog.showErrorMesg(msg)
                    }
                }
                "female"->{
                    if (weight_input.text.toString().isNotEmpty() &&
                        heightin_input.text.toString().isNotEmpty() &&
                        age_input.text.toString().isNotEmpty() &&
                        heightft_input.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Female_calculate()
                    }
                    else{

                        var msg=""
                        val dialog=ErrorDialogs(requireContext())
                        if (weight_input.text.toString().isEmpty()){
                            msg="\nPlease provide positive weight value."
                        }
                        if (heightin_input.text.toString().isEmpty()){
                            msg=msg.plus("\nPlease provide positive height in inches value.")
                        }

                        if (age_input.text.toString().isEmpty()){
                            msg=msg.plus("\nPlease provide an age between 5 and 100.")
                        }

                        if (heightft_input.text.toString().isEmpty()){
                            msg=msg.plus("\nPlease provide positive height in Foot value.")
                        }
                        dialog.showErrorMesg(msg)
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

        viewModel?.calculate_BMI_Male(wt,ht_ft,ht_in,age)
    }
    private fun take_input_BMI_Female_calculate(){
        val wt=weight_input.text.toString().toBigDecimal()
        val ht_ft=heightft_input.text.toString().toBigDecimal()
        val ht_in=heightin_input.text.toString().toBigDecimal()
        val age=age_input.text.toString().toBigDecimal()

        viewModel?.calculate_BMI_Female(wt,ht_ft,ht_in,age)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel= ViewModelProvider(this).get(BMIViewModel::class.java)

    }


}