package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.Dialogs.ErrorDialog
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.calorie_counter_u_i.*
import java.math.BigDecimal


private const val TAG="Calorie_Calculator"


/**
 * A simple [Fragment] subclass.
 * Use the [Calorie_Calculator.newInstance] factory method to
 * create an instance of this fragment.
 */
class Calorie_Calculator : Fragment(R.layout.calorie_counter_u_i), SelectListener,AdapterView.OnItemSelectedListener   {

    private var ActivityLevel = arrayOf("Sedentary(little or no exercise)", "Light(exercise 1–3 times/week)", "Moderate(exercise 4–5 times/week)", "Active(intense exercise 6–7 times/week)")


    //    To select AMR from the dropdown
    //    TODO Variable to be cleared after Reset with the dropdown value
    private var selecteditem=""
    private var gender="male"
    private var food:ArrayList<FoodItems> =ArrayList()

    var viewModel: CalorieCounterViewModel?=null


    private fun take_input_BMI_Male_calculate(wt: BigDecimal,ht_ft: BigDecimal,ht_in: BigDecimal,age: BigDecimal) {
        viewModel?.calculate_BMR_Male(wt,ht_ft,ht_in,age)
    }
    private fun take_input_BMI_Female_calculate( wt: BigDecimal,ht_ft: BigDecimal,ht_in: BigDecimal,age: BigDecimal){
         viewModel?.calculate_BMR_Female(wt,ht_ft,ht_in,age)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.initializeitems(food)
        recyclerview.layoutManager=LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        recyclerview.adapter=FoodAdapter(food,this)

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.

        Spinner.onItemSelectedListener = this

        //Creating the ArrayAdapter instance having the ActivityLevel list
        //Creating the ArrayAdapter instance having the ActivityLevel list
        val adapter = activity?.let {
            ArrayAdapter(it,R.layout.dropdown_item,ActivityLevel)
        }

        adapter?.setDropDownViewResource(R.layout.dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        //Setting the ArrayAdapter data on the Spinner
        Spinner.adapter = adapter

        btn_boy.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_girl.setBackgroundColor(Color.parseColor("#9E7777"))
            gender="male"
            viewModel?.genderAnim(it,gender)
        }

        btn_girl.setOnClickListener{
            it.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_boy.setBackgroundColor(Color.parseColor("#9E7777"))
            gender="female"
            viewModel?.genderAnim(it,gender)
        }


        submit_button.setOnClickListener {
            Log.d(TAG,"Total Calorie Consumed as a whole is ${totalCalorieConsumed()}")
            val errorDialog= ErrorDialog(requireActivity())

//            https://github.com/PatilShreyas/MaterialDialog-Android
            when(gender){
                "male"->{
                    if (weight_inputi.text.toString().isNotEmpty() &&
                        heightin_inputi.text.toString().isNotEmpty() &&
                        age_inputi.text.toString().isNotEmpty() &&
                        heightft_inputi.text.toString().isNotEmpty())
                    {
                        val wt=weight_inputi.text.toString().toBigDecimal()
                        val ht_ft=heightft_inputi.text.toString().toBigDecimal()
                        val ht_in=heightin_inputi.text.toString().toBigDecimal()
                        val age=age_inputi.text.toString().toBigDecimal()

                        take_input_BMI_Male_calculate(wt,ht_ft,ht_in,age)
                    }
                    else{

                       var msg=""
                        if (weight_inputi.text.toString().isEmpty()){
                            msg="Please provide positive weight value."
                            weight_inputi.error=msg
                        }
                        if (heightin_inputi.text.toString().isEmpty()){
                            msg=("Please provide positive height in inches value.")
                            heightin_inputi.error=msg
                        }

                        if (age_inputi.text.toString().isEmpty()){
                            msg=("Please provide an age between 5 and 100.")
                            age_inputi.error=msg
                        }

                        if (heightft_inputi.text.toString().isEmpty()){
                            msg=("\nPlease provide positive height in Foot value.")
                            heightft_inputi.error=msg
                        }
                        errorDialog.ErrorDialog()
                    }
                }
                "female"->{
                    if (weight_inputi.text.toString().isNotEmpty() &&
                        heightin_inputi.text.toString().isNotEmpty() &&
                        age_inputi.text.toString().isNotEmpty() &&
                        heightft_inputi.text.toString().isNotEmpty())
                    {
                        val wt=weight_inputi.text.toString().toBigDecimal()
                        val ht_ft=heightft_inputi.text.toString().toBigDecimal()
                        val ht_in=heightin_inputi.text.toString().toBigDecimal()
                        val age=age_inputi.text.toString().toBigDecimal()
                        take_input_BMI_Female_calculate(wt,ht_ft,ht_in,age)
                    }
                    else{
                        var msg=""
                        if (weight_inputi.text.toString().isEmpty()){
                            msg="Please provide positive weight value."
                            weight_inputi.error=msg
                        }
                        if (heightin_inputi.text.toString().isEmpty()){
                            msg=("Please provide positive height in inches value.")
                            heightin_inputi.error=msg
                        }

                        if (age_inputi.text.toString().isEmpty()){
                            msg=("Please provide an age between 5 and 100.")
                            age_inputi.error=msg
                        }

                        if (heightft_inputi.text.toString().isEmpty()){
                            msg=("\nPlease provide positive height in Foot value.")
                            heightft_inputi.error=msg
                        }
                        errorDialog.ErrorDialog()
                    }
                }
            }

            val fragment: Fragment = CalorieOutputFragment()
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container_view, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }


    private fun totalCalorieConsumed():Double{
        var counter=0
        var totCalorie = 0.0
        food.forEach {
            totCalorie += food[counter].getMnoOfItems() * food[counter].getMCalorie()
            counter++

        }
        return totCalorie
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel= ViewModelProvider(this).get(CalorieCounterViewModel::class.java)
    }


}






