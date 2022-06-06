package com.aurosaswatraj.countmycrunch.HealthBlogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.activity_health_vlogs.*
import java.util.ArrayList

class HealthVlogActivity : AppCompatActivity(),iSelectListener {

    private var vlogs: ArrayList<HealthVlogs> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_vlogs)

        VlogContents().initialize_vlogs(vlogs)
        recyclerVlogs.layoutManager=LinearLayoutManager(applicationContext,
            RecyclerView.VERTICAL,false)

        recyclerVlogs.adapter=VlogAdapter(vlogs,this)



    }

    override fun onVlogSelected(vlogLink: String?) {
        intent = Intent(applicationContext, BlogScreen::class.java)
                 .putExtra("link",vlogLink)
        startActivity(intent)
        finish()
    }
}