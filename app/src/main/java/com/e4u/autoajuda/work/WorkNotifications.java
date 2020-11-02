package com.e4u.autoajuda.work;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.e4u.autoajuda.HomeActivity;
import com.e4u.autoajuda.R;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WorkNotifications extends Worker {

    private final String CHANNEL_ID = "personal_channel";
    private static final String uniqueWorkName = "com.e4u.autoajuda.work.WorkNotifications";
    private static final long repeatIntervalMin = 6;
    private static final long flexIntervalMin = 1;

    public WorkNotifications(
            @NonNull Context context,
            @NonNull WorkerParameters params) {

        super(context, params);
    }

    private static PeriodicWorkRequest getOwnWorkRequest() {
        return new PeriodicWorkRequest.Builder(
                WorkNotifications.class, repeatIntervalMin, TimeUnit.HOURS, flexIntervalMin, TimeUnit.HOURS
        ).build();
    }

    public static void enqueueSelf() {
        WorkManager.getInstance().enqueueUniquePeriodicWork(uniqueWorkName, ExistingPeriodicWorkPolicy.KEEP, getOwnWorkRequest());
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour < 11) {
            showNotificationMorning();
        } else if (hour >= 11 && hour < 18) {
            showNotificationAfterNoon();
        } else if (hour >= 18) {
            showNotificationForNight();
        }

        return ListenableWorker.Result.success();
    }

    void showNotificationMorning() {

        String title = "Exercício do dia";
        String message = "Pratique o exercício do dia. Todo dia uma novidade para você.";
        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Exercício",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Novo Exercício");
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


    void showNotificationAfterNoon() {

        String title = "Frase do dia";
        String message = "Leia a frase motivacional do dia e compartilhe.";
        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Frase",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Frase do dia");
            mNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent intentLista = new Intent(getApplicationContext(), HomeActivity.class);
        intentLista.putExtra("notificationTarde", "YES");
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intentLista, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());

    }


    void showNotificationForNight() {

        String title = "Que tal meditar um pouco?";
        String message = "Clique aqui e faça uma meditação relaxante";
        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Meditação",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Meditação");
            mNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent intentLista = new Intent(getApplicationContext(), HomeActivity.class);
        intentLista.putExtra("notificationNoite", "YES");
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intentLista, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}
