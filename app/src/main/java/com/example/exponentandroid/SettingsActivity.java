package com.example.exponentandroid;

import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeUnit;

public class SettingsActivity extends AppCompatActivity {

    TextView userEmail;
    Button userLogout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;

    private static final String CHANNEL_ID = "CHANNEL_ID";
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        userEmail = findViewById(R.id.tvUserEmail);
        userLogout = findViewById(R.id.btnLogout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        userEmail.setText(firebaseUser.getEmail());

        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        setContentView(R.layout.settings_activity);
        b1 = findViewById(R.id.button);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setAutoCancel(false)
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setWhen(System.currentTimeMillis())
                                .setContentIntent(pendingIntent)
                                .setContentTitle("ALARM")
                                .setContentText("SOMEONE IS TRYING TO STEAL YOUR CAR")
                                .setPriority(PRIORITY_HIGH);
                createChannelIfNeeded(notificationManager);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
            }
        });
    }
    public static void createChannelIfNeeded(NotificationManager manager) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }
}