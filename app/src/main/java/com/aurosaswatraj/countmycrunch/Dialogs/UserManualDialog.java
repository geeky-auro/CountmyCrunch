package com.aurosaswatraj.countmycrunch.Dialogs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.aurosaswatraj.countmycrunch.manuals.UserManual;
import com.crowdfire.cfalertdialog.CFAlertDialog;

public class UserManualDialog {


     public void showDialog(Context context){
        // Create Alert using Builder
         Intent i =new Intent(context, UserManual.class);
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(context)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
                .setTitle("User Guide")
                .setCancelable(true)
                .setMessage("What is CountMyCrunch All about..! Let’s Know more about the insights of our application.")
                .setDialogBackgroundColor(Color.parseColor("#DEBA9D"))
                .addButton("BMI Calculator", -1, Color.parseColor("#774E4E"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                    String strName = "https://medium.com/@aurosaswatraj/user-manual-of-bmi-calculator-22ce96dd09c7";
                    i.putExtra("STRING_I_NEED", strName);
                    context.startActivity(i);
                    dialog.dismiss();
                })

                .addButton("Calorie Counter", -1, Color.parseColor("#774E4E"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                    String strName = "https://medium.com/@aurosaswatraj/user-manual-of-calorie-counter-972df34a8009";
                    i.putExtra("STRING_I_NEED", strName);
                    context.startActivity(i);
                    dialog.dismiss();
                })
            .addButton("Cancel", Color.WHITE, Color.GRAY, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.CENTER, (dialog, which) -> {

             dialog.dismiss();
         })
                .setCornerRadius(50.0f);


// Show the alert
        builder.show();
    }
}
