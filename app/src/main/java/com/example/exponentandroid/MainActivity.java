package com.example.exponentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button login_button;
    Button registration_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button = (Button) findViewById(R.id.login_button);
        registration_button = (Button) findViewById(R.id.registration_button);
    }
    public void OnClickSignIn(View view){
        Intent intent1 = new Intent(this,menu_activity.class);
        startActivity(intent1);
    }
    public void OnClickSignUp(View view){
        Intent intent2 = new Intent(this,registration_activity.class);
        startActivity(intent2);
    }
}