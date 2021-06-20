package com.example.quizapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class QuestionActivity extends AppCompatActivity {
    TextView description;
    OptionAdapter optionAdapter;
    RecyclerView optionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        description = (TextView)findViewById(R.id.description);
        optionList = (RecyclerView)findViewById(R.id.option_list);
        binView();
        setupFirebase();
    }

    private void setupFirebase() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Quizes").whereEqualTo("id","20-06-2021").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("DATA",queryDocumentSnapshots.toObjects(Quiz.class).get(0).questions.toString());
            }
        });

    }

    private void binView() {
        Question question = new Question("Which is the only bird that can fly backwards",
                "Sunbird",
                "Kingfisher",
                "Honey eater",
                "Hummingbird",
                "Hummingbird");
        description.setText(question.description);
        optionList.setLayoutManager(new LinearLayoutManager(this));
        optionAdapter = new OptionAdapter(question,this) ;
        optionList.setAdapter(optionAdapter);
        optionList.setHasFixedSize(true);

    }


}