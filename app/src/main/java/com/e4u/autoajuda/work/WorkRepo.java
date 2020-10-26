package com.e4u.autoajuda.work;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class WorkRepo {

    public void startWorkNotifications(){

        PeriodicWorkRequest workNotifications = new PeriodicWorkRequest
                .Builder(WorkNotifications.class, 15, TimeUnit.MINUTES, 15, TimeUnit.MINUTES)
                .setConstraints(Constraints.NONE)
                .build();


        WorkManager.getInstance().enqueueUniquePeriodicWork("PeriodWorkManager", ExistingPeriodicWorkPolicy.KEEP, workNotifications);
    }
}
