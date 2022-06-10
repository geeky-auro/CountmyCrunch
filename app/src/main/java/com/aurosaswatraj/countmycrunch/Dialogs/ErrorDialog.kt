package com.aurosaswatraj.countmycrunch.Dialogs

import android.app.Activity
import android.view.Gravity
import com.thecode.aestheticdialogs.*

class ErrorDialog(activity:Activity) {

    private val activity=activity

    fun ErrorDialog(){
        AestheticDialog.Builder(activity, DialogStyle.FLAT, DialogType.ERROR)
            .setTitle("ERROR!")
            .setMessage("Please provide appropriate details!")
            .setCancelable(true)
            .setDarkMode(true)
            .setGravity(Gravity.CENTER)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                    //actions...
                }
            })
            .show()
    }

    fun warningDarkMode(){
        AestheticDialog.Builder(activity, DialogStyle.FLAT, DialogType.WARNING)
            .setTitle("Warning!")
            .setMessage("Please turn off the Dark Mode for best User Experience..!")
            .setCancelable(true)
            .setDarkMode(true)
            .setGravity(Gravity.CENTER)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                    //actions...
                }
            })
            .show()
    }

}