package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button signInBtn;
    private TextView registerText;
    private EditText email, password;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mauth = FirebaseAuth.getInstance();
        signInBtn = (Button)findViewById(R.id.btn_login);
        email = (EditText)findViewById(R.id.et_email_address);
        password = (EditText)findViewById(R.id.et_password);
        registerText = (TextView)findViewById(R.id.btn_sign_up);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = email.getText().toString();
                String passwordText = password.getText().toString();

                if (emailAddress.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Email is empty",Toast.LENGTH_SHORT).show();
                }
                else if(passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password is empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    signIn(emailAddress,passwordText);
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
            }
        });

    }


    private void signIn(String email, String password){
        mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("EmailPassword","SignINWithEmail:Success");
                    FirebaseUser user = mauth.getCurrentUser();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else{
                    Log.d("EmailPassword","SignINWithEmail:Fail", task.getException());
                    Toast.makeText(getApplicationContext(),"Authentication Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}