package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView userEmailText;
    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        userEmailText = (TextView)findViewById(R.id.user_email);
        logoutBtn = (Button)findViewById(R.id.logout_btn);
        userEmailText.setText(firebaseAuth.getCurrentUser().getEmail());

        logoutBtn.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(this,LoginIntro.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))   ;
            finish();

        });
    }


}