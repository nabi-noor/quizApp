package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro);

        Button nextBtn = (Button) findViewById(R.id.btn_get_started);
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect(1);
            }
        });

        if (mauth.getCurrentUser() != null){
            Toast.makeText(this,"User is already logged in",Toast.LENGTH_SHORT).show();
            redirect(0);
        }


    }

    private void redirect(int i){
        Intent intents[] = {
                new Intent(LoginIntro.this,MainActivity.class),
                new Intent(LoginIntro.this, LoginActivity.class)
        };
        try {
            startActivity(intents[i]);
            finish();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}