package com.entriver.fieldgeofencingandlocationupdates;

import android.content.Context;

import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobManager;

import androidx.annotation.NonNull;

public class AddReceiver extends JobCreator.AddJobCreatorReceiver {
    @Override
    protected void addJobCreator(@NonNull Context context, @NonNull JobManager manager) {
//        manager.addJobCreator(new DemoJobCreator());
    }
}
