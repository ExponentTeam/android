package com.example.exponentandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button login_button;
    EditText email_edittext, password_edittext;
    Button registration_button;
    private FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        login_button = (Button) findViewById(R.id.login_button);
        registration_button = (Button) findViewById(R.id.registration_button);
        email_edittext = findViewById(R.id.email_edittext);
        password_edittext = findViewById(R.id.password_edittext);
        Auth = FirebaseAuth.getInstance();
    }

    public void OnClickSignIn(View view) {
        if (!TextUtils.isEmpty(email_edittext.getText().toString()) && !TextUtils.isEmpty(password_edittext.getText().toString())) {

            Auth.signInWithEmailAndPassword(email_edittext.getText().toString(), password_edittext.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Logged in successfully!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), menu_activity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Wrong email or password!", Toast.LENGTH_SHORT).show();
                    }
                }


            });

        }
        else{
            Toast.makeText(getApplicationContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void OnClickSignUp(View view){
        Intent intent2 = new Intent(this, registration_activity.class);
        startActivity(intent2);
    }
}