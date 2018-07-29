package com.pt29.crypto.Sync;

import android.app.NotificationManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.pt29.crypto.R;
import com.pt29.crypto.Widget.NewAppWidget;

import java.util.concurrent.TimeUnit;

/**
 * Created by parham on 3/2/2018.
 */

public class SyncJob extends Job {
    public static final String TAG = "job_tag";

    @Override
    @NonNull
    protected Result onRunJob(@NonNull Params params) {
        Log.i("LOG39","job done!");



        Intent intent = new Intent(getContext(), NewAppWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int ids[] = AppWidgetManager.
                getInstance(getContext().getApplicationContext()).
                getAppWidgetIds(new ComponentName(getContext().getApplicationContext(), NewAppWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        getContext().sendBroadcast(intent);





        return Result.SUCCESS;
    }

    public static void scheduleJob() {
        new JobRequest.Builder(SyncJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setRequirementsEnforced(true)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }
}
