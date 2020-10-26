package com.e4u.autoajuda.work;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.e4u.autoajuda.HomeActivity;
import com.e4u.autoajuda.R;

public class WorkNotifications extends Worker {

    private final String CHANNEL_ID = "personal_channel";
    private final int NOTIFICATION_ID = 001;

    public WorkNotifications(
            @NonNull Context context,
            @NonNull WorkerParameters params) {

        super(context, params);
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {

        showNotification();
        return ListenableWorker.Result.success();
    }

    void showNotification() {

        String title = "Exercício do dia";
        String message = "Pratique o exercício do dia. Todo dia uma novidade para você!";
        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Novo",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Novo vídeo");
            mNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent intentLista = new Intent(getApplicationContext(), HomeActivity.class);
        intentLista.putExtra("notification", "YES");
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intentLista, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}
