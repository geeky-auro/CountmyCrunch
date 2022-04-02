package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.extensions.LayoutContainer

private const val TAG="FoodAdapter"
class FoodViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer{
    private val image: ImageView =containerView.findViewById(R.id.foodimage)
    private val name: TextView =containerView.findViewById(R.id.foodname)
    private val cardBtn: CardView =containerView.findViewById(R.id.cardBtn)
    private val addBtn: Button =containerView.findViewById(R.id.add_item_btn)
    private val subBtn: Button =containerView.findViewById(R.id.sub_item_btn)
    private val textcounter: TextView =containerView.findViewById(R.id.num_item_text)
    fun bind(listener: FoodAdapter.onTaskClickListener, currentItem: FoodItems){
        image.setImageResource(currentItem.getMimgae())
        name.text = currentItem.getMtext()
//        var counter=0
//
//        cardBtn.setOnClickListener {
//            Log.d(TAG,"Card Clicked and Calorie is ${currentItem.getMCalorie()}")
//        }
//
//        addBtn.setOnClickListener {
//            counter++
////            totalCalorie += currentItem.getMCalorie() * counter
//            textcounter.text=counter.toString()
//        }
//
//        subBtn.setOnClickListener {
//            counter--
////            totalCalorie -= currentItem.getMCalorie() * counter
//            textcounter.text=counter.toString()
//        }

    }
    }

class FoodAdapter(val food:ArrayList<FoodItems>,private val context: Context,private val listener:onTaskClickListener): RecyclerView.Adapter<FoodViewHolder>() {




    interface onTaskClickListener{


        fun recyclerViewListClicked(v: View?, position: Int)


//      Now that we've defined the interface, we can pass in a reference to something that implements that interface,
//      so that the adapter knows what to call.(Adding in the primary Constructor)
    }



    var totalCalorie=0.0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.food_list_item,parent,false)
        return FoodViewHolder(view)
    }



    fun getCalorie():Double{
        return totalCalorie
    }

    override fun getItemCount(): Int {
        return food.size
    }




    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem:FoodItems= food[position]
        holder.bind(listener,currentItem)
    }

}