package com.aurosaswatraj.countmycrunch.CalorieCounter

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
import kotlinx.android.synthetic.main.food_list_item.view.*

class FoodViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer{
    private val image: ImageView =containerView.findViewById(R.id.foodimage)
    private val name: TextView =containerView.findViewById(R.id.foodname)
    private val textcounter: TextView =containerView.findViewById(R.id.num_item_text)
    fun bind( currentItem: FoodItems){
        image.setImageResource(currentItem.getMimgae())
        name.text = currentItem.getMtext()
        textcounter.text=currentItem.getMnoOfItems().toString()

    }
    }

class FoodAdapter(val food:ArrayList<FoodItems>,private val selectListener:SelectListener): RecyclerView.Adapter<FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.food_list_item,parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return food.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem:FoodItems= food[position]

        holder.itemView.add_item_btn.setOnClickListener {
            selectListener.onAddItemClicked(food[position],position)
        }

        holder.itemView.sub_item_btn.setOnClickListener {
            selectListener.onSubItemClicked(food[position],position)
        }

        holder.bind(currentItem)
    }

}