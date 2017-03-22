package com.cesarynga.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                showNotification(buildBasicNotification());
                break;
            case 1:
                showNotification(buildNotificationWithBackStack());
                break;
            case 2:
                showNotification(buildNotificationWithActions());
                break;
        }
    }

    private void showNotification(Notification notification) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);
    }

    private Notification buildBasicNotification() {
        Intent resultIntent = new Intent(this, ResultActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(Notification.PRIORITY_HIGH) // priority High makes notification shows as head-up
                        .setSmallIcon(R.drawable.ic_notification) // notification icon, required
                        .setColor(Color.parseColor("#71b32a"))
                        .setContentTitle("My notification")
                        .setContentText("Basic notification")
                        .setAutoCancel(true) // remove the notification from status bar
                        .setContentIntent(resultPendingIntent);

        return builder.build();
    }

    private Notification buildNotificationWithBackStack() {
        Intent resultIntent = new Intent(this, ResultActivity.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(ResultActivity.class);
        taskStackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(Notification.PRIORITY_HIGH) // priority High makes notification shows as head-up
                        .setSmallIcon(R.drawable.ic_notification) // notification icon, required
                        .setColor(Color.parseColor("#71b32a"))
                        .setContentTitle("My notification")
                        .setContentText("Notification with back stack")
                        .setAutoCancel(true) // remove the notification from status bar
                        .setContentIntent(resultPendingIntent);

        return builder.build();
    }

    private Notification buildNotificationWithActions() {
        Intent resultIntent = new Intent(this, ResultActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(Notification.PRIORITY_HIGH) // priority High makes notification shows as head-up
                        .setSmallIcon(R.drawable.ic_notification) // notification icon, required
                        .setColor(Color.parseColor("#71b32a"))
                        .setContentTitle("My notification")
                        .setContentText("Notification with actions")
                        .addAction(R.drawable.ic_dismiss, "Dismiss", resultPendingIntent)
                        .addAction(R.drawable.ic_snooze, "Snooze", resultPendingIntent)
                        .setAutoCancel(true) // remove the notification from status bar
                        .setContentIntent(resultPendingIntent);

        return builder.build();
    }
}
