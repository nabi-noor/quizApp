package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button signInBtn;
    private EditText email, password;
    private String emailAddress, passwordText;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mauth = FirebaseAuth.getInstance();
        signInBtn = (Button)findViewById(R.id.btn_login);
        email = (EditText)findViewById(R.id.et_email_address);
        password = (EditText)findViewById(R.id.et_password);
    }
}