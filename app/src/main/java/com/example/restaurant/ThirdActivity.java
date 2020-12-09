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

import java.util.Objects;

public class ThirdActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText remail;
    private TextInputEditText rpassword;
    NotificationManager manager;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        Toast.makeText(this,"Please Register",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        remail=findViewById(R.id.remail);
        rpassword=findViewById(R.id.rpassword);
        mAuth=FirebaseAuth.getInstance();
        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    public void signup(View view){
        String re= Objects.requireNonNull(remail.getText()).toString();
        String rp= Objects.requireNonNull(rpassword.getText()).toString();
        if(re.isEmpty()||rp.isEmpty()) {
            Toast.makeText(this, "Enter a valid Email and Password ", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(re,rp).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Log.i("TAG","Success");
                    Toast.makeText(ThirdActivity.this,"Register Success",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ThirdActivity.this,SecondActivity.class);
                    startActivity(intent);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("channel id", "android channel", NotificationManager.IMPORTANCE_DEFAULT);
                        manager.createNotificationChannel(channel);
                    }
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"channel id");
                    builder.setContentTitle("Restaurant");
                    builder.setSmallIcon(R.drawable.rest);
                    builder.setContentText("User Registered Successfully");
                    builder.setAutoCancel(true);
                    manager.notify(1,builder.build());
                }
                else{
                    Toast.makeText(ThirdActivity.this,"Failure",Toast.LENGTH_SHORT).show();
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("channel id", "android channel", NotificationManager.IMPORTANCE_DEFAULT);
                        manager.createNotificationChannel(channel);
                    }
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"channel id");
                    builder.setContentTitle("Restaurant");
                    builder.setSmallIcon(R.drawable.rest);
                    builder.setContentText("User Registration Failed");
                    builder.setAutoCancel(true);
                    manager.notify(1,builder.build());
                }
            });
        }
    }
    }