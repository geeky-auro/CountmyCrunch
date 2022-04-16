package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ViewPagerAdapter(val image: List<CalorieData>):
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage=image[position]
        holder.itemView.mtitle.text=curImage.getTitle()
        holder.itemView.mFoodDisplay.text=curImage.getFoodDisplay()
        holder.itemView.swipeNext.text=curImage.getswipeNext()
    }

    override fun getItemCount(): Int {
        return image.size
    }

}