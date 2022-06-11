package com.aurosaswatraj.countmycrunch.Chronometer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.extensions.LayoutContainer


class LapViewHolder(override val containerView: View ) : RecyclerView.ViewHolder(containerView),LayoutContainer{

    private val lap_counter:TextView= containerView.findViewById(R.id.lap_counter)
    private val lap_time:TextView= containerView.findViewById(R.id.lap_time)

    fun bind(currentItem:Laps){

        lap_counter.text=currentItem.lapCounter.toString()
        lap_time.text=currentItem.lapTime

    }

}

class ChronometerLapAdapter(val LapsData:ArrayList<Laps>):RecyclerView.Adapter<LapViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LapViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.listlap_item,parent,false)
        return LapViewHolder(view)
    }

    override fun onBindViewHolder(holder: LapViewHolder, position: Int) {
        val currentItem:Laps=LapsData[position]

        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
      return LapsData.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


}