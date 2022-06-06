package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
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
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow


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

    private var viewModel: CalorieCounterViewModel?=null


    private var task: Track? = null
    private var listener1: OnSaveClicked? = null

    interface OnSaveClicked {
        fun onSaveClicked()
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

                       var msg:String
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
                        var msg: String
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



    private fun taskfromUI() : Track{

//        At the moment the saveTask function is using values from the EditText widgets in the layout.
//        Thats where we come to need taskfromUI function

        val date = Date()
        val formatter = SimpleDateFormat("dd/MM/yy")
        val str: String = formatter.format(date)
//        To get the value of SortOrder
        val sorOrder=0
//        we'll make our new task, assign it an ID, and then return it.
        val newTask=Track(gender,age_inputi.text.toString(),
            heightft_inputi.text.toString()+"Feet "+heightin_inputi.text.toString()+"Inches ",
            weight_inputi.text.toString()+"Kg"+"\n"+"\nIdeal Weight:${idealWeightCalculate(heightft_inputi.text.toString().toDouble(),heightin_inputi.text.toString().toDouble())} Kg",
                totalCalorieConsumed().toBigDecimal(),
                  requiredConsume(weight_inputi.text.toString().toDoubleOrNull(),
                      heightft_inputi.text.toString().toDoubleOrNull(),
                      heightin_inputi.text.toString().toDoubleOrNull(),
                      age_inputi.text.toString().toDoubleOrNull(),
                      gender,selecteditem
                  )

         ,str,sorOrder)
        newTask.id=task?.id?:0
//        I used the Elvis operator (?:) on the above line, to assign the value 0 if the fragment's task is null.
        return newTask
    }

//    TODO: Required Consumed need to be implemented.>!

    private fun idealWeightCalculate(height_ft:Double,height_in:Double):String{
        val height_in_meter=height_ft * 0.3048 + height_in * 0.0254
        val idealWeight=22* height_in_meter.pow(2.0)
        val df = DecimalFormat("0.00")
        df.setRoundingMode(RoundingMode.UP)
        return df.format(idealWeight).toString()


    }


    private fun requiredConsume(
        wt: Double?, ht_ft: Double?, ht_in: Double?, age: Double?,gender: String?,activityLvl:String
    ):String{


        when(gender){
            "female"->{
                val heightincm= ht_ft!! * 30.48 + ht_in!! * 2.54
                var BMR: Double? = wt!! * 9.563 + heightincm * 1.850 + age!! * 4.676
                BMR=BMR!!+655.1
                when(activityLvl) {
                    "Sedentary" -> {
                        val Sedentary= BMR *(1.2)
                        return Sedentary.toString()
                    }
                    "Lightly" -> {
                        val Lightly_active= BMR *(1.375)
                        return Lightly_active.toString()
                    }
                    "Moderate" -> {
                        val Moderately_active= BMR *(1.55)
                        return Moderately_active.toString()
                    }
                    "Active" -> {
                        val Active = BMR *(1.725)
                        return Active.toString()
                    }
                    else -> {
                        val Moderately_active= BMR *(1.55)
                        return Moderately_active.toString()
                    }
                }
            }

            else->{
                val male_weight=wt
                val male_height_ft=ht_ft
                val male_height_in=ht_in
                val male_age=age
                val heightincm= male_height_ft!!*30.48+male_height_in!!*2.54
                var BMR: Double? =male_weight!!*13.75+heightincm*5.003-male_age!!*6.755
                BMR=BMR!!+66.47


                when(activityLvl) {
                    "Sedentary" -> {
                        val Sedentary=BMR *(1.2)
                        return Sedentary.toString()
                    }
                    "Lightly" -> {
                        val Lightly_active=BMR *(1.375)
                        return Lightly_active.toString()
                    }
                    "Moderate" -> {
                        val Moderately_active=BMR *(1.55)
                        return Moderately_active.toString()
                    }
                    "Active" -> {
                        val Active =BMR *(1.725)
                        return Active.toString()
                    }
                    else -> {
                        val Moderately_active=BMR *(1.55)
                        return Moderately_active.toString()
                    }
                }
            }

        }

    }









    private fun saveTask(){
//        Create a newTask Object with the details to be saved, then
//        Call the viewmodel's saveTask function to save it
//        Task is now a data class , so we can compare the new details with the original task,
//        and only save if they are different.


//        We'll create our new Task object, and we'll get the values from our EditText widgets.
        val newTask=taskfromUI()
//        Then we'll check to see if it's the same task that we were given, when the fragment was created.(the task that we are editing)
//       We can compare two task instances, because a data class provides an equals function for us.
        if (newTask != task){
            Log.d(TAG,"saveTask, saving task , id is ${newTask.id}")
//            We will call the save task function in the viewmodel
            task=viewModeli.saveTask(newTask)
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
        save?.setOnClickListener {
            saveTask()
            listener1?.onSaveClicked()
            resetvariables()
            btn_boy.setBackgroundColor(Color.parseColor("#774E4E"))
            btn_girl.setBackgroundColor(Color.parseColor("#774E4E"))
            weight_inputi.setText("")
            heightin_inputi.setText("")
            age_inputi.setText("")
            heightft_inputi.setText("")
            gender="male"
            alertDialog.dismiss()
//            Code for saving

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
        if (context is OnSaveClicked) {
            listener1 = context
        } else {
            throw RuntimeException("$context Must implement OnSaveClicked")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener=null
    }




    override fun onOutputSent(data: ArrayList<CalorieData>) {
        fragmentB?.updateEditText(data)
    }


}






