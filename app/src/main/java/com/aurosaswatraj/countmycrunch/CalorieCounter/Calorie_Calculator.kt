package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.aurosaswatraj.countmycrunch.Dialogs.ErrorDialog
import com.aurosaswatraj.countmycrunch.ProgressTracking.Track
import com.aurosaswatraj.countmycrunch.ProgressTracking.TrackViewModel
import com.aurosaswatraj.countmycrunch.R
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.calorie_counter_u_i.*
import kotlinx.android.synthetic.main.calorie_counter_u_i.btn_boy
import kotlinx.android.synthetic.main.calorie_counter_u_i.btn_girl
import kotlinx.android.synthetic.main.calorie_counter_u_i.submit_button
import kotlinx.android.synthetic.main.fragment_b_m_i_finder.*
import java.math.BigDecimal

private const val TAG="Calorie_Calculator"

/**
 * A simple [Fragment] subclass.
 * Use the [Calorie_Calculator.newInstance] factory method to
 * create an instance of this fragment.
 *
 *
 */

private const val ARG_TASK = "task"

interface FragmentCalorieOutput {
    fun onOutputSent(data: ArrayList<CalorieData>)
}

class Calorie_Calculator : Fragment(R.layout.calorie_counter_u_i), SelectListener,AdapterView.OnItemSelectedListener,FragmentCalorieOutput{

    private var ActivityLevel = arrayOf("Sedentary(little or no exercise)", "Light(exercise 1–3 times/week)", "Moderate(exercise 4–5 times/week)", "Active(intense exercise 6–7 times/week)")

    private var listener: FragmentCalorieOutput? = null
    private var fragmentB: CalorieOutputFragment? = null

    //    To select AMR from the dropdown
    //    TODO Variable to be cleared after Reset with the dropdown value
    private var selecteditem=""
    private var gender="male"
    private var food:ArrayList<FoodItems> =ArrayList()
    private var data:ArrayList<CalorieData> = ArrayList()

    var viewModel: CalorieCounterViewModel?=null


    private var task: Track? = null
    private var listener1: OnSaveClicked? = null

    interface OnSaveClicked {

    }

    //    View Model variable declaration
    private val viewModeli by lazy {
        // ViewModelProviders.of(activity!!).get(TaskTimerViewModel::class.java)
        ViewModelProvider(this).get(TrackViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Starts")
        task = arguments?.getParcelable(ARG_TASK)
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
        fragmentB = CalorieOutputFragment()
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
            data.add(0,CalorieData("Total Calorie Consumed",totalFoodConsumed()+"\n"+"Total Calorie:${totalCalorieConsumed()}","Swipe Next>>"))

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
                        showoutputDialog(data)
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
                        showoutputDialog(data)
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


            Log.d(TAG,"List Data:$data")
            Log.d(TAG,"List Data Size:${data.size}")

        }

    }

    private fun showoutputDialog(data: ArrayList<CalorieData>) {
        val alertDialog: AlertDialog = AlertDialog.Builder(requireContext()).create()
        val view: View? = requireActivity().getLayoutInflater().inflate(R.layout.fragment_calorie_output, null)
        alertDialog.setCancelable(false)
        val viewpager=view?.findViewById<ViewPager2>(R.id.viewPager)
        val adapter=ViewPagerAdapter(data)
        viewpager?.adapter=adapter
        val cancel=view?.findViewById<MaterialButton>(R.id.cance_btn)
        val save=view?.findViewById<MaterialButton>(R.id.save_bitn)
        cancel?.setOnClickListener {
            alertDialog.dismiss()
        }
        save?.setOnClickListener {
            resetvariables()
            btn_boy.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_girl.setBackgroundColor(Color.parseColor("#774E4E"))
            weight_inputi.setText("")
            heightin_inputi.setText("")
            age_inputi.setText("")
            heightft_inputi.setText("")
            gender="male"
            alertDialog.dismiss()
        }
        alertDialog.setView(view)
        alertDialog.show()

    }

    private fun take_input_BMI_Male_calculate(wt: BigDecimal,ht_ft: BigDecimal,ht_in: BigDecimal,age: BigDecimal) {
        val calculatedData=viewModel?.calculate_BMR_Male(wt,ht_ft,ht_in,age)
        data.add(CalorieData("Estimated Daily Calorie Needs",calculatedData!!,""))
    }
    private fun take_input_BMI_Female_calculate( wt: BigDecimal,ht_ft: BigDecimal,ht_in: BigDecimal,age: BigDecimal){
        val calculatedData=viewModel?.calculate_BMR_Female(wt,ht_ft,ht_in,age)
        data.add(CalorieData("Estimated Daily Calorie Needs",calculatedData!!,""))
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

    private fun resetvariables(){
        food.clear()
        viewModel?.initializeitems(food)
        recyclerview.adapter?.notifyDataSetChanged()
        data.clear()

    }

    private fun totalFoodConsumed():String{
        var counter=0
        var foodData = ""
        food.forEach {
            if (food[counter].getMnoOfItems()>0){
                foodData=foodData.plus(food[counter].name+":X"+food[counter].getMnoOfItems()+"\n")
            }
            counter++

        }
        return foodData
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel= ViewModelProvider(this).get(CalorieCounterViewModel::class.java)
        listener = if (context is FragmentCalorieOutput) {
            context
        } else {
            throw RuntimeException("$context must implement FragmentAListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener=null
    }


    private fun switchFragements(data: ArrayList<CalorieData>) {

//        var result=Bundle()
//        result.putParcelable("Example List",CalPareceable(
//            data[0].Disptitle, data[0].mfoodDisplay,
//            data[0].swipeNext))
//        result.putParcelable("Example List",CalPareceable(
//            data[1].Disptitle, data[1].mfoodDisplay,
//            data[1].swipeNext))
//        https://stackoverflow.com/questions/39867847/android-passing-arraylistmodel-to-fragment-from-activity
        val fragment: Fragment = CalorieOutputFragment()
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onOutputSent(data: ArrayList<CalorieData>) {
        fragmentB?.updateEditText(data)
    }


}






