package com.aurosaswatraj.countmycrunch.BMIFinder


import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.aurosaswatraj.countmycrunch.Dialogs.ErrorDialog
import com.aurosaswatraj.countmycrunch.R
import com.thecode.aestheticdialogs.*
import kotlinx.android.synthetic.main.calorie_counter_u_i.*
import kotlinx.android.synthetic.main.fragment_b_m_i_finder.*
import kotlinx.android.synthetic.main.fragment_b_m_i_finder.btn_boy
import kotlinx.android.synthetic.main.fragment_b_m_i_finder.btn_girl
import kotlinx.android.synthetic.main.fragment_b_m_i_finder.submit_button
import java.math.BigDecimal


private const val TAG = "BMIFinder"


/**
 * A simple [Fragment] subclass.
 * Use the [BMIFinder.newInstance] factory method to
 * create an instance of this fragment.
 */


class BMIFinder : Fragment(R.layout.fragment_b_m_i_finder) {

    private var viewModel:BMIViewModel?=null
    private var gender="male"



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit_Button()
        genderSelection()
    }

    private fun genderSelection(){
        btn_boy.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#FF0000"))
            btn_girl.setBackgroundColor(Color.parseColor("#FFFFFF"))
            gender="male"
            Log.d(TAG, gender)
            viewModel?.goback(it,gender)
        }

        btn_girl.setOnClickListener{
            it.setBackgroundColor(Color.parseColor("#FF0000"))
            btn_boy.setBackgroundColor(Color.parseColor("#FFFFFF"))
            gender="female"
            Log.d(TAG, gender)
            viewModel?.goback(it,gender)
        }

    }

    private fun submit_Button(){

        val listener=View.OnClickListener {

            val errorDialog=ErrorDialog(requireActivity())
            when(gender){
                "male"->{
                    if (weight_input.text.toString().isNotEmpty() &&
                        heightin_input.text.toString().isNotEmpty() &&
                        age_input.text.toString().isNotEmpty() &&
                        heightft_input.text.toString().isNotEmpty())
                    {
                        take_input_BMI_Male_calculate()
//                        Male:56.2 kg + 1.41 kg per inch over 5 feet
//                        Female:53.1 kg + 1.36 kg per inch over 5 feet
//
                    }
                    else{
                    var msg:String
                        if (weight_input.text.toString().isEmpty()){
                            msg="Please provide positive weight value."
                            weight_input.error=msg
                        }
                        if (heightin_input.text.toString().isEmpty()){
                            msg=("Please provide positive height in inches value.")
                            heightin_input.error=msg
                        }

                        if (age_input.text.toString().isEmpty()){
                            msg=("Please provide an age between 5 and 100.")
                            age_input.error=msg
                        }

                        if (heightft_input.text.toString().isEmpty()){
                            msg=("Please provide positive height in Foot value.")
                            heightft_input.error=msg
                        }
//
                        errorDialog.ErrorDialog()
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
                        if (weight_input.text.toString().isEmpty()){
                            msg="Please provide positive weight value."
                            weight_input.error=msg
                        }
                        if (heightin_input.text.toString().isEmpty()){
                            msg=("Please provide positive height in inches value.")
                            heightin_input.error=msg
                        }

                        if (age_input.text.toString().isEmpty()){
                            msg=("Please provide an age between 5 and 100.")
                            age_input.error=msg
                        }

                        if (heightft_input.text.toString().isEmpty()){
                            msg=("\nPlease provide positive height in Foot value.")
                            heightft_input.error=msg
                        }
                        errorDialog.ErrorDialog()
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

        val BMI=viewModel?.calculate_BMI_Male(wt,ht_ft,ht_in,age)
        customAlertDialogi(BMI)
    }
    private fun take_input_BMI_Female_calculate(){
        val wt=weight_input.text.toString().toBigDecimal()
        val ht_ft=heightft_input.text.toString().toBigDecimal()
        val ht_in=heightin_input.text.toString().toBigDecimal()
        val age=age_input.text.toString().toBigDecimal()

        val BMI=viewModel?.calculate_BMI_Female(wt,ht_ft,ht_in,age)
        customAlertDialogi(BMI)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel= ViewModelProvider(this).get(BMIViewModel::class.java)

    }

    fun customAlertDialogi(BMI:BigDecimal?) {
        val alertDialog: AlertDialog = AlertDialog.Builder(requireContext()).create()
        val view: View? = requireActivity().layoutInflater.inflate(R.layout.bmi_results, null)
        alertDialog.setCancelable(false)
        val AGE: TextView = view?.findViewById<View>(R.id.BMIAge) as TextView
        AGE.text=AGE.text.toString().plus(age_input.text.toString())
        val HEIGHT: TextView = view.findViewById<View>(R.id.BMIHeight) as TextView
        HEIGHT.text=HEIGHT.text.toString().plus(heightft_input.text.toString()+"ft "+heightin_input.text.toString()+"in")
        val STATUS: TextView = view.findViewById<View>(R.id.BMIStatus) as TextView
        STATUS.text=viewModel?.showRecommendations(BMI)
        val WEIGHT: TextView = view.findViewById<View>(R.id.BMIWeight) as TextView
        WEIGHT.text=WEIGHT.text.toString().plus(weight_input.text.toString()+"Kg")
        val BMICALCULLATED: TextView = view.findViewById<View>(R.id.BMICalculate) as TextView
        BMICALCULLATED.text=BMICALCULLATED.text.toString().plus("$BMI")
        val lottie = view.findViewById<LottieAnimationView>(R.id.result_anim)

        when(viewModel?.showRecommendations(BMI)){
            "Underweight"->{
                lottie.setAnimation(R.raw.under_final)
            }
            "Normal weight"->{
                lottie.setAnimation(R.raw.normal_1)
            }
            "OverWeight"->{lottie.setAnimation(R.raw.overweight_2)
            }
            "Obesity"->{
                lottie.setAnimation(R.raw.obesity_3)
            }
            else->{   lottie.setAnimation(R.raw.normal)   }

        }


        val OK = view.findViewById<View>(R.id.OK_btn) as Button
        OK.setOnClickListener { alertDialog.dismiss() }
        alertDialog.setView(view)
        alertDialog.show()

    }





}