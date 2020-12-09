package com.example.restaurant;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SecondActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText email;
    private TextInputEditText password;
    NotificationManager manager;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        email = findViewById(R.id.remail);
        password = findViewById(R.id.rpassword);
        mAuth = FirebaseAuth.getInstance();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    public void signin(View view) {
        String e = email.getText().toString();
        String p = password.getText().toString();
        if (e.isEmpty() || p.isEmpty()) {
            Toast.makeText(this, "Enter valid a Email id ", Toast.LENGTH_SHORT).show();
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                NotificationChannel channel=new NotificationChannel("channel id","android channel",NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"channel id");
            builder.setContentTitle("Restaurant");

            builder.setContentText("Enter Valid Details");

            builder.setSmallIcon(R.drawable.rest);

            builder.setAutoCancel(true);

            manager.notify(1,builder.build());
        }
        else {
            mAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Log.i("TAG", "Success");
                    Toast.makeText(SecondActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userId = user.getUid();
                    Intent intent = new Intent(SecondActivity.this, FourthActivity.class);
                    startActivity(intent);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        NotificationChannel channel=new NotificationChannel("channel id","android channel",NotificationManager.IMPORTANCE_DEFAULT);
                        manager.createNotificationChannel(channel);
                    }
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"channel id");
                    builder.setContentTitle("Restaurant");

                    builder.setContentText("Login Successful");

                    builder.setSmallIcon(R.drawable.rest);

                    builder.setAutoCancel(true);

                    manager.notify(1,builder.build());
                } else {
                    Toast.makeText(SecondActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        NotificationChannel channel=new NotificationChannel("channel id","android channel",NotificationManager.IMPORTANCE_DEFAULT);
                        manager.createNotificationChannel(channel);
                    }
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"channel id");
                    builder.setContentTitle("Restaurant");

                    builder.setContentText("Login Failed");

                    builder.setSmallIcon(R.drawable.rest);

                    builder.setAutoCancel(true);

                    manager.notify(1,builder.build());
                }
            });
        }
    }
}