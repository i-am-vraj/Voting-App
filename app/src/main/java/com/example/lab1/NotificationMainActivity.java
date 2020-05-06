package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import static com.example.lab1.channelID.CHANNEL_ID;

public class NotificationMainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);

        notificationManager = NotificationManagerCompat.from(this);
    }

    public void showNotification(View v) {
        RemoteViews collapsedView = new RemoteViews(getPackageName(),
                R.layout.notification_collapsed);
        RemoteViews expandedView = new RemoteViews(getPackageName(),
                R.layout.notification_expanded);

        Intent clickIntent = new Intent(this, NotificationReceiver.class);

        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(this,
                0, clickIntent, 0);

        collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Hello World! Collasped is called");

        expandedView.setImageViewResource(R.id.image_view_expanded, R.drawable.modiji);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent);

        Notification notification = new NotificationCompat.Builder(NotificationMainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                //.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .build();

        notificationManager.notify(1, notification);
    }
}
