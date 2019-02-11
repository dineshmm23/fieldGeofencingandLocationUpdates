package com.entriver.fieldgeofencingandlocationupdates;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.util.Log;

import com.evernote.android.job.DailyJob;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * @author rwondratschek
 */
public class DemoSyncJob extends DailyJob implements LocationUpdatesComponent.ILocationProvider {

    public static final String TAG = "job_demo_tag";
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 65;

    public static int scheduleJob() {
        return new JobRequest.Builder(DemoSyncJob.TAG)
                .startNow()
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setUpdateCurrent(true)

                .build()
                .schedule();
    }

    public static void dailySchedule() {

        // Set the alarm to start at 8:30 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY, 21);

        calendar2.set(Calendar.MINUTE, 30);

        String s = String.format("TIme in millisec--%s-------%s\n%s----%s", calendar.getTimeInMillis(), calendar2.getTimeInMillis(),TimeUnit.HOURS.toMillis(0), TimeUnit.HOURS.toMillis(22));
        Log.e(TAG, "dailySchedule: " + s);
        // schedule between 8am and 8 pm
//        DailyJob.schedule(new JobRequest.Builder(TAG)
//                        .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
//                        .setUpdateCurrent(true)
//                , calendar2.getTimeInMillis(), calendar.getTimeInMillis());
        DailyJob.schedule(new JobRequest.Builder(TAG)
                        .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                        .setUpdateCurrent(true)
                , TimeUnit.HOURS.toMillis(0), TimeUnit.HOURS.toMillis(22));
    }

    static void cancelJob(int jobId) {
        if (jobId > 0)
            JobManager.instance().cancel(jobId);
    }

    @Override
    public void onLocationUpdate(Location location) {

        Log.d("Location Update:", location.getLatitude() + "," + location.getLongitude());
        Utils.updateLocationInfo(location, getContext());

    }

    @NonNull
    @Override
    protected DailyJobResult onRunDailyJob(@NonNull Params params) {
        LocationUpdatesComponent locationUpdatesComponent = new LocationUpdatesComponent(this);

        locationUpdatesComponent.onCreate(getContext());
        locationUpdatesComponent.onStart();

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), MainActivity.class), 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(TAG, "Job Demo", NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("Job demo job");
            getContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(getContext(), TAG)
                .setContentTitle("ID " + params.getId())
                .setContentText("Job ran, exact " + params.isExact() + " , periodic " + params.isPeriodic() + ", transient " + params.isTransient())
                .setAutoCancel(true)
                .setChannelId(TAG)
                .setSound(null)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setShowWhen(true)
                .setColor(Color.GREEN)
                .setLocalOnly(true)
                .build();

        NotificationManagerCompat.from(getContext()).notify(new Random().nextInt(), notification);

        return DailyJobResult.SUCCESS;
    }

//    @NonNull
//    @Override
//    protected Result onRunJob(@NonNull Params params) {
//        boolean success = new DemoSyncEngine(getContext()).sync();
//        LocationUpdatesComponent locationUpdatesComponent = new LocationUpdatesComponent(this);
//
//        locationUpdatesComponent.onCreate(getContext());
//        locationUpdatesComponent.onStart();
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), MainActivity.class), 0);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(TAG, "Job Demo", NotificationManager.IMPORTANCE_LOW);
//            channel.setDescription("Job demo job");
//            getContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
//        }
//
//        Notification notification = new NotificationCompat.Builder(getContext(), TAG)
//                .setContentTitle("ID " + params.getId())
//                .setContentText("Job ran, exact " + params.isExact() + " , periodic " + params.isPeriodic() + ", transient " + params.isTransient())
//                .setAutoCancel(true)
//                .setChannelId(TAG)
//                .setSound(null)
//                .setContentIntent(pendingIntent)
//                .setSmallIcon(R.drawable.ic_stat_name)
//                .setShowWhen(true)
//                .setColor(Color.GREEN)
//                .setLocalOnly(true)
//                .build();
//
//        NotificationManagerCompat.from(getContext()).notify(new Random().nextInt(), notification);
//
//        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return Result.FAILURE;
//        }
//        return Result.SUCCESS;
//    }
}
