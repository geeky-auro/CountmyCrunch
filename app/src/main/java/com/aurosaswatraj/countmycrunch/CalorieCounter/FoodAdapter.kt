package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import de.hdodenhof.circleimageview.*
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R

private const val TAG="FoodAdapter"

class FoodAdapter(val food:ArrayList<FoodItems>):RecyclerView.Adapter<FoodAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.food_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var currentItem:FoodItems= food[position]
        holder.image.setImageResource(currentItem.getMimgae())
        holder.name.text = currentItem.getMtext()
        holder.cardBtn.setOnClickListener {
            Log.d(TAG,"Card Clicked and Calorie is ${currentItem.getMCalorie()}")
        }

    }

    override fun getItemCount(): Int {
        return food.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image=itemView.findViewById<ImageView>(R.id.foodimage)
        val name=itemView.findViewById<TextView>(R.id.foodname)
        val cardBtn=itemView.findViewById<CardView>(R.id.cardBtn)
    }

}