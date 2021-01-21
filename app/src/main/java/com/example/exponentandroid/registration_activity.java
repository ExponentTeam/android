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

public class registration_activity extends AppCompatActivity {
    EditText email2_edittext, password2_edittext, reenter_password2_edittext;
    private FirebaseAuth Auth;
    Button registration2_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        init();
    }

    private void init(){
        registration2_button = (Button) findViewById(R.id.registration2_button);
        email2_edittext = findViewById(R.id.email2_edittext);
        password2_edittext = findViewById(R.id.password2_edittext);
        reenter_password2_edittext = findViewById(R.id.reenter_password2_edittext);
        Auth = FirebaseAuth.getInstance();
    }
    public void onClickRegistration(View view){
        String email = email2_edittext.getText().toString().trim();
        String pass = password2_edittext.getText().toString().trim();
        String re_pass = reenter_password2_edittext.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            if (TextUtils.isEmpty(pass)){
                if (!pass.equals(re_pass)){
                    password2_edittext.setError("Password is required field");
                    email2_edittext.setError("Email is required field");
                    reenter_password2_edittext.setError("Passwords do not match");
                    return;
                }
                password2_edittext.setError("Password is required field");
                email2_edittext.setError("Email is required field");
                return;
            }
            email2_edittext.setError("Email is required field");
            return;
        }
        if (TextUtils.isEmpty(pass)){
            password2_edittext.setError("Password is required field");
            return;
        }
        if (!(pass.equals(re_pass))){
            reenter_password2_edittext.setError("Passwords do not match");
            return;
        }

        Auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Signed up successfully!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}