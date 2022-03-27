package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import de.hdodenhof.circleimageview.*
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R

private const val TAG="FoodAdapter"

class FoodAdapter(val food:ArrayList<FoodItems>):RecyclerView.Adapter<FoodAdapter.ViewHolder>() {


    private var totalCalorie=0.0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.food_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentItem:FoodItems= food[position]
        holder.image.setImageResource(currentItem.getMimgae())
        holder.name.text = currentItem.getMtext()
        var counter=0
        holder.cardBtn.setOnClickListener {
            Log.d(TAG,"Card Clicked and Calorie is ${currentItem.getMCalorie()}")
        }
        holder.addBtn.setOnClickListener {
            counter++
            totalCalorie+=currentItem.getMCalorie()
            holder.textcounter.text=counter.toString()
        }
        holder.subBtn.setOnClickListener {
            counter--
            totalCalorie+=currentItem.getMCalorie()
            holder.textcounter.text=counter.toString()
        }

    }

    fun getCalorie():Double{
        return totalCalorie
    }

    override fun getItemCount(): Int {
        return food.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.findViewById(R.id.foodimage)
        val name: TextView =itemView.findViewById(R.id.foodname)
        val cardBtn: CardView =itemView.findViewById(R.id.cardBtn)
        val addBtn: Button =itemView.findViewById(R.id.add_item_btn)
        val subBtn: Button =itemView.findViewById(R.id.sub_item_btn)
        val textcounter: TextView =itemView.findViewById(R.id.num_item_text)

    }

}