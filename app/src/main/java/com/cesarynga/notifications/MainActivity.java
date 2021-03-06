package com.cesarynga.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
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
    NotificationCompat.InboxStyle inboxStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
        inboxStyle = new NotificationCompat.InboxStyle();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                showNotification(buildBasicNotification(), 1);
                break;
            case 1:
                showNotification(buildNotificationWithBackStack(), 2);
                break;
            case 2:
                showNotification(buildNotificationWithActions(3), 3);
                break;
            case 3:
                showNotification(buildNotificationWithBigTextStyle(), 4);
                break;
            case 4:
                showNotification(buildNotificationWithInboxStyle(), 5);
                break;
        }
    }

    private void showNotification(Notification notification, int id) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(id, notification);
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

    private Notification buildNotificationWithActions(int id) {
        Intent dismissIntent = new Intent(this, DismissReceiver.class);
        dismissIntent.putExtra("notification_id", id);

        Intent snoozeIntent = new Intent(this, ResultActivity.class);
        snoozeIntent.putExtra("notification_id", id);

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(this, 0,
                dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent snoozePendingIntent = PendingIntent.getActivity(this, 0,
                snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(Notification.PRIORITY_HIGH) // priority High makes notification shows as head-up
                        .setSmallIcon(R.drawable.ic_notification) // notification icon, required
                        .setColor(Color.parseColor("#71b32a"))
                        .setContentTitle("My notification")
                        .setContentText("Notification with actions")
                        .addAction(R.drawable.ic_dismiss, "Dismiss", dismissPendingIntent)
                        .addAction(R.drawable.ic_snooze, "Snooze", snoozePendingIntent)
                        .setAutoCancel(true); // remove the notification from status bar

        return builder.build();
    }

    private Notification buildNotificationWithBigTextStyle() {
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
                        .setContentText("Big text style notification")
                        .setAutoCancel(true) // remove the notification from status bar
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum leo sem, tincidunt et mi a, gravida sodales leo. Cras molestie sit amet diam eget interdum. In auctor tortor non velit fringilla accumsan. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean in varius nibh, sed cursus odio. Donec suscipit ultricies est non ullamcorper. Maecenas condimentum et sapien eget fermentum. Donec feugiat imperdiet tellus ut iaculis. Fusce sit amet viverra lectus, vitae vulputate quam. Pellentesque maximus erat ac metus finibus posuere."))
                        .setContentIntent(resultPendingIntent);

        return builder.build();
    }

    private Notification buildNotificationWithInboxStyle() {
        Intent resultIntent = new Intent(this, ResultActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        inboxStyle.addLine("New event");
        inboxStyle.setSummaryText("My new events");
        inboxStyle.setBigContentTitle("Big content text");

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(Notification.PRIORITY_HIGH) // priority High makes notification shows as head-up
                        .setSmallIcon(R.drawable.ic_notification) // notification icon, required
                        .setColor(Color.parseColor("#71b32a"))
                        .setContentTitle("My notification")
                        .setContentText("Inbox style notification")
                        .setAutoCancel(true) // remove the notification from status bar
                        .setStyle(inboxStyle)
                        .setContentIntent(resultPendingIntent);

        return builder.build();
    }
}
