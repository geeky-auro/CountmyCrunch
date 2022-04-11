package com.aurosaswatraj.countmycrunch.Dialogs

import android.content.Context
import android.graphics.Color
import com.example.flatdialoglibrary.dialog.FlatDialog

class ErrorDialogs(context: Context) {

    val context=context

    fun showErrorMesg(textMsg:String){
        val flatDialog = FlatDialog(context)
        flatDialog.setTitle("Please provide appropriate details!")
            .setSubtitle("$textMsg")
            .setSecondButtonText("Alright")
            .setBackgroundColor(Color.parseColor("#d63031"))
            .setSecondButtonColor(Color.parseColor("#ff7675"))
            .withSecondButtonListner {
                flatDialog.dismiss()
            }
            .show()
    }

}