package com.pt29.crypto.Sync;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.evernote.android.job.Job;


/**
 * Created by parham on 2/11/2018.
 */

public class SyncJobCreator implements com.evernote.android.job.JobCreator {

    @Override
    @Nullable
    public Job create(@NonNull String tag) {
        switch (tag) {
            case SyncJob.TAG:
                return new SyncJob();
            default:
                return null;
        }
    }
}