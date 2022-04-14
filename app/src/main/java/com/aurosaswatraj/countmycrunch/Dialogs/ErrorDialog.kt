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

}