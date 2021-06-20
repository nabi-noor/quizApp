package com.example.quizapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapters.QuizViewAdapter;
import com.example.quizapp.models.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar appBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout mainDrawer;
    private List<Quiz> quizzes = new ArrayList<>();
    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private QuizViewAdapter quizViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDrawer = (DrawerLayout)findViewById(R.id.main_drawer);
        appBar =(Toolbar)findViewById(R.id.top_bar);
        recyclerView = (RecyclerView)findViewById(R.id.quiz_recycler_view);
        setupView();

    }

    private void setupFirestore() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Quizes");
        collectionReference.addSnapshotListener((value, error) -> {
            if (value == null || error != null){
                Log.d("ErrorFirebase",error.getMessage());
                return;
            }

            Log.d("DATA",value.toObjects(Quiz.class).toString());
            quizzes.addAll(value.toObjects(Quiz.class));
            quizViewAdapter.notifyDataSetChanged();
            Log.d("DATA",quizzes.get(0).questions.toString());


        });
    }

    private void setupView(){
        setupFirestore();
        setupDrawer();
        setupRecyclerView();
        setupDatePicker();
    }

    private void setupDatePicker() {
        FloatingActionButton btnDatePicker = (FloatingActionButton) findViewById(R.id.btn_date_picker);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().build();
                materialDatePicker.show(getSupportFragmentManager(),"DatePicker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        Log.d("DATEPICKER",materialDatePicker.getHeaderText());
                        Intent i = new Intent(getApplicationContext(),QuestionActivity.class);
                        i.putExtra("date",materialDatePicker.getHeaderText());
                        startActivity(i);
                    }
                });

                materialDatePicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("DATEPICKER","Date picker was cancelled");
                    }
                });

                materialDatePicker.addOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.d("DATEPICKER","Date picker was not proceeded");
                    }
                });
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        quizViewAdapter = new QuizViewAdapter(quizzes);
        recyclerView.setAdapter(quizViewAdapter);
    }

    private void setupDrawer(){
        setSupportActionBar(appBar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mainDrawer,R.string.app_name,R.string.app_name);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

}