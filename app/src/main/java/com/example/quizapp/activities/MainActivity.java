package com.example.quizapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapters.QuizViewAdapter;
import com.example.quizapp.models.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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


        });
    }

    private void setupView(){
        setupFirestore();
        setupDrawer();
        setupRecyclerView();
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