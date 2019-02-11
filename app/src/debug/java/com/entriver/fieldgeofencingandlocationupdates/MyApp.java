package com.entriver.fieldgeofencingandlocationupdates;

import android.app.Application;
import android.os.StrictMode;

import com.evernote.android.job.JobManager;


public class MyApp extends Application {

    private static MyApp appInstance;

    public synchronized static MyApp getInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        JobManager.create(this).addJobCreator(new DemoJobCreator());
    }

}
