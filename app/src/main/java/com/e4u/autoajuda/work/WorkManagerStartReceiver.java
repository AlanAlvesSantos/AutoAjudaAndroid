package com.e4u.autoajuda.work;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class WorkManagerStartReceiver extends BroadcastReceiver {
    WorkManager mWorkManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        PeriodicWorkRequest.Builder myWorkBuilder =
                new PeriodicWorkRequest.Builder(WorkNotifications.class,
                        15,
                        TimeUnit.MINUTES,
                        15,
                        TimeUnit.MINUTES);

        PeriodicWorkRequest myWork = myWorkBuilder.build();
        mWorkManager = WorkManager.getInstance(context);
        mWorkManager.enqueue(myWork);

    }
}
