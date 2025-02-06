package com.aurosaswatraj.countmycrunch.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import com.aurosaswatraj.countmycrunch.manuals.UserManual;

public class UserManualDialog {

    public void showDialog(Context context) {
        final String[] options = {"About Us", "BMI Calculator", "Calorie Counter", "Contact Us"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("User Guide")
                .setItems(options, (dialog, which) -> {
                    String url;
                    switch (which) {
                        case 0:
                            url = "https://countmycrunch.netlify.app/about.html";
                            break;
                        case 1:
                            url = "https://countmycrunch.netlify.app/bmicalc";
                            break;
                        case 2:
                            url = "https://countmycrunch.netlify.app/caloriecalc.html";
                            break;
                        case 3:
                            url = "https://countmycrunch.netlify.app/contactus.html";
                            break;
                        default:
                            url = "";
                            break;
                    }

                    if (!url.isEmpty()) {
                        Intent intent = new Intent(context, UserManual.class);
                        intent.putExtra("STRING_I_NEED", url);
                        context.startActivity(intent);
                    }
                    dialog.dismiss(); // Properly dismissing the dialog
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()); // Dismiss on Cancel

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
