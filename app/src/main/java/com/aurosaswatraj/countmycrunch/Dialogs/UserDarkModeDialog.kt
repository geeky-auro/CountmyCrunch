package com.aurosaswatraj.countmycrunch.Dialogs

import android.app.Activity
import android.content.Context
import android.content.res.Configuration

class UserDarkModeDialog {

    fun darkModeDialog(context:Context,activity:Activity){
        val darkModeDialog:ErrorDialog= ErrorDialog(activity)
        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                darkModeDialog.warningDarkMode()
            }
            Configuration.UI_MODE_NIGHT_NO -> {}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                darkModeDialog.warningDarkMode()
            }
        }
    }


}