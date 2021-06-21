package com.example.quizapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapters.OptionAdapter;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.Quiz;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    TextView description;
    OptionAdapter optionAdapter;
    RecyclerView optionList;

    List<Quiz> quizzes;
    Map<String,Question> questions;
    int index;

    Button next, previous, submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        description = (TextView)findViewById(R.id.description);
        optionList = (RecyclerView)findViewById(R.id.option_list);
        quizzes = new ArrayList<Quiz>();
        questions = Map.of();
        index = 1;
        next = (Button)findViewById(R.id.btn_next);
        previous = (Button)findViewById(R.id.btn_previous);
        submit = (Button)findViewById(R.id.btn_submit);
        next.setVisibility(View.GONE);
        previous.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        setupFirebase();
        setupEventLinstners();
    }

    private void setupEventLinstners() {
        previous.setOnClickListener(v -> {
            index --;
            bindView();
        });

        next.setOnClickListener(v -> {
            index++;
            bindView();
        });

        submit.setOnClickListener(v -> {
            Log.d("FINISH", "Quiz Finished");
            Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
            String json = new Gson().toJson(quizzes.get(0));
            Log.d("FINISH",json);
            intent.putExtra("QUIZ",json);
            startActivity(intent);
        });
    }

    private void setupFirebase() {
        String date = getIntent().getStringExtra("DATE");
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        if (date != null){
            firebaseFirestore.collection("Quizes").whereEqualTo("title",date).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()){
                        Log.d("DATA",queryDocumentSnapshots.toObjects(Quiz.class).get(0).toString());
                        quizzes.addAll(queryDocumentSnapshots.toObjects(Quiz.class));
                        questions = quizzes.get(0).questions;
                        bindView();
                    }

                }
            });

        }
    }

    private void bindView() {

        if (index == 1 && questions.size() != 1){
            next.setVisibility(View.VISIBLE);
        }else if(index == questions.size()){
            submit.setVisibility(View.VISIBLE);
            previous.setVisibility(View.VISIBLE);
        }else{
            previous.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
        Question question = questions.get("question"+index);
        description.setText(question.description);
        optionList.setLayoutManager(new LinearLayoutManager(this));
        optionAdapter = new OptionAdapter(question,this) ;
        optionList.setAdapter(optionAdapter);
        optionList.setHasFixedSize(true);

    }


}