package com.aurosaswatraj.countmycrunch.HealthBlogs

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurosaswatraj.countmycrunch.Dialogs.UserDarkModeDialog
import com.aurosaswatraj.countmycrunch.R
import kotlinx.android.synthetic.main.activity_health_vlogs.*
import java.util.*


class HealthVlogActivity : AppCompatActivity(),iSelectListener {

    private var vlogs: ArrayList<HealthVlogs> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_vlogs)
        val darkModeDialog= UserDarkModeDialog()
        darkModeDialog.darkModeDialog(this,this)

        val window = this.window
        window.statusBarColor = Color.parseColor("#2f3640")

        VlogContents().initialize_vlogs(vlogs)
        recyclerVlogs.layoutManager=LinearLayoutManager(applicationContext,
            RecyclerView.VERTICAL,false)
        vlogs.shuffle()
        recyclerVlogs.adapter=VlogAdapter(vlogs,this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            RearrangeItems()
        }


    }

    private fun RearrangeItems() {
        // Shuffling the data of ArrayList using system time
        vlogs.shuffle(Random(System.currentTimeMillis()))
        recyclerVlogs.adapter=VlogAdapter(vlogs,this)
    }

    override fun onVlogSelected(vlogLink: String?) {
        intent = Intent(applicationContext, BlogScreen::class.java)
                 .putExtra("link",vlogLink)
        startActivity(intent)
        finish()
    }
}