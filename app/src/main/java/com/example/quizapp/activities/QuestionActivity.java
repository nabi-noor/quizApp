package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.adapters.OptionAdapter;
import com.example.quizapp.models.Question;

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