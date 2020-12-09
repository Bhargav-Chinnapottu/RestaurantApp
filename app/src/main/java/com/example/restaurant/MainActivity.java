package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view){
        Button login=(Button)findViewById(R.id.login);
        Toast.makeText(this,"Login Account",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    public void register(View view){
        Button register=(Button)findViewById(R.id.register);
        Toast.makeText(this,"Create Account",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }
}