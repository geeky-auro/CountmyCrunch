package com.aurosaswatraj.countmycrunch.Dialogs

import android.content.Context
import android.graphics.Color
import com.example.flatdialoglibrary.dialog.FlatDialog

class ErrorDialogs {

    fun showErrorMesg(textMsg:String, context: Context){
        var flatDialog = FlatDialog(context)
        flatDialog.setTitle("Please provide appropriate details!")
            .setSubtitle(textMsg)
            .setFirstButtonText("Alright")
            .setBackgroundColor(Color.parseColor("#d63031"))
            .setFirstButtonColor(Color.parseColor("#ff7675"))
            .withFirstButtonListner {
                flatDialog.dismiss()
            }
            .show()
    }

}