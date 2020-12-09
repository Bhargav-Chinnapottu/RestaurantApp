package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class FourthActivity extends AppCompatActivity {
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    public void restaurantsList(View view) {
        Intent intent=new Intent(this,SixActivity.class);
        startActivity(intent);
    }

    public void orders(View view) {
        Intent intent=new Intent(this,SevenActivity.class);
        startActivity(intent);
    }

    public void contact(View view) {
        Intent intent=new Intent(this,EightActivity.class);
        startActivity(intent);

    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this,"SignOut successfully",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel id","android channel",NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"channel id");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.rest);
        builder.setContentText("User Logged out Successfully");
        builder.setContentTitle("Restaurant");
        manager.notify(1,builder.build());

    }
}