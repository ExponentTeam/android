package com.example.exponentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.login_button: Intent intent = new Intent(this, menu_activity.class);
            startActivity(intent);
            break;
        default: break;}
    }
}