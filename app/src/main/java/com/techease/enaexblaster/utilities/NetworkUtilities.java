package com.techease.enaexblaster.utilities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.text.Spanned;
import android.widget.Toast;

public class NetworkUtilities {

    public static void sendMail(Context context, String string){

        String body = "Welcome to Enaex Blaster App\n\nclick here   "+string;

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Enaex blaster");
        i.putExtra(Intent.EXTRA_TEXT   , body);

        try {
           context.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
