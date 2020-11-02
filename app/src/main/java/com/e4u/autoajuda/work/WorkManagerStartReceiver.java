package com.e4u.autoajuda.work;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WorkManagerStartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() == null || !intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
            return;

        WorkNotifications.enqueueSelf();
    }
}
