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
                .setDialogBackgroundColor(Color.WHITE)


                .addButton("About Us", Color.WHITE, Color.parseColor("#301934"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                    String strName = "https://countmycrunch.netlify.app/about.html";
                    i.putExtra("STRING_I_NEED", strName);
                    context.startActivity(i);
                    dialog.dismiss();
                })

                .addButton("BMI Calculator", Color.WHITE, Color.BLACK, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                    String strName = "https://countmycrunch.netlify.app/bmicalc";
                    i.putExtra("STRING_I_NEED", strName);
                    context.startActivity(i);
                    dialog.dismiss();
                })

                .addButton("Calorie Counter", Color.WHITE, Color.BLACK, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                    String strName = "https://countmycrunch.netlify.app/caloriecalc.html";
                    i.putExtra("STRING_I_NEED", strName);
                    context.startActivity(i);
                    dialog.dismiss();
                })

                .addButton("Contact Us", Color.WHITE, Color.parseColor("#023020"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                    String strName = "https://countmycrunch.netlify.app/contactus.html";
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
