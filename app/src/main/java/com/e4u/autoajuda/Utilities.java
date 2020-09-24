package com.e4u.autoajuda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utilities {

    public static String NEWS_URL = "http://www.webinfoco.com.br/filesla/AA/newsAA.json";
    public static String VIDEOS_URL = "http://www.webinfoco.com.br/filesla/AA/videosAA.json";

    public static AlertDialog.Builder buildErrorMessageDialog(Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder;
    }

    public static AlertDialog.Builder buildMessageDialog(Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder;
    }
}
