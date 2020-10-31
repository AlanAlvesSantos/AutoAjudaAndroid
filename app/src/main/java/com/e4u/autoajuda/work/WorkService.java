package com.e4u.autoajuda.work;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class WorkService extends JobIntentService {

    private static final int JOB_ID = 2;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, WorkService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        PeriodicWorkRequest workNotifications = new PeriodicWorkRequest
                .Builder(WorkNotifications.class, 6, TimeUnit.HOURS, 60, TimeUnit.MINUTES)
                .setConstraints(Constraints.NONE)
                .build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("workNotifications", ExistingPeriodicWorkPolicy.KEEP, workNotifications);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
