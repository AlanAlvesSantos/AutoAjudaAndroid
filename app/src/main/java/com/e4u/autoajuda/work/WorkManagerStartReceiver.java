package com.e4u.autoajuda.work;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WorkManagerStartReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent mIntent = new Intent(context, WorkService.class);
            WorkService.enqueueWork(context, mIntent);
        }

        /*
        WorkManager mWorkManager;
        PeriodicWorkRequest.Builder myWorkBuilder =
                new PeriodicWorkRequest.Builder(WorkNotifications.class,
                        15,
                        TimeUnit.MINUTES,
                        15,
                        TimeUnit.MINUTES);

        PeriodicWorkRequest myWork = myWorkBuilder.build();
        mWorkManager = WorkManager.getInstance(context);
        mWorkManager.enqueue(myWork); */
    }
}
