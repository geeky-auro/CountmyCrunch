package com.aurosaswatraj.countmycrunch.HealthBlogs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_vlogs.view.*
import java.lang.Exception

class VlogsViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    private val image_vlog: ImageView =containerView.findViewById(R.id.image_vlog)
    private val title_vlog: TextView =containerView.findViewById(R.id.title_vlog)
    private val description_vlog: TextView =containerView.findViewById(R.id.description_vlog)
    var picasso =Picasso.Builder(containerView.getContext()).listener { picasso, uri, exception ->
            Log.d("TAG", " onImageLoadFailed message")

            exception.printStackTrace()
        }.build()
    fun bind( currentItem: HealthVlogs){
        picasso.setIndicatorsEnabled(true)
        picasso.load(currentItem.getMimgae())
            .fit()
            .centerCrop()
            .into(image_vlog, object : Callback {

                override fun onSuccess() {
                    Log.d("TAG","Do nothing")
                }

                override fun onError(e: Exception?) {

                    val generateRandomeImageNum:Int=(Math.random()*(6-1+1)+1).toInt()
                    val imageLink:String
                    when(generateRandomeImageNum){
                        1->{
                            imageLink="https://i.ytimg.com/vi/K26QQopxvW0/maxresdefault.jpg"
                        }
                        2->{
                               imageLink="https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/healthy_eating_for_weight_loss_ref_guide/650x350_healthy_eating_for_weight_loss_ref_guide.jpg"
                        }
                        3->{
                            imageLink="https://images.pexels.com/photos/3621168/pexels-photo-3621168.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                        }
                        4->{
                            imageLink="https://images.pexels.com/photos/4498155/pexels-photo-4498155.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                        }
                        5->{
                            imageLink="https://images.pexels.com/photos/235922/pexels-photo-235922.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                        }
                        6->{
                            imageLink="https://images.pexels.com/photos/1279330/pexels-photo-1279330.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                        }
                        else->{
                            imageLink="https://images.pexels.com/photos/5907810/pexels-photo-5907810.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                        }
                    }

                    Picasso.get().load(imageLink).into(image_vlog)
                }
            })

        title_vlog.text = currentItem.getMtitle()
        title_vlog.isSelected=true
        description_vlog.text=currentItem.getMDescription()

    }
}

class VlogAdapter(val content_vlogs:ArrayList<HealthVlogs>,private val selectListener: iSelectListener): RecyclerView.Adapter<VlogsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VlogsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_vlogs,parent,false)
        return VlogsViewHolder(view)
    }

    override fun onBindViewHolder(holder: VlogsViewHolder, position: Int) {
        val currentItem: HealthVlogs = content_vlogs[position]

        holder.itemView.mainCard.setOnClickListener {
            selectListener.onVlogSelected(currentItem.getMVlogLink())
        }


        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
       return content_vlogs.size
    }


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}