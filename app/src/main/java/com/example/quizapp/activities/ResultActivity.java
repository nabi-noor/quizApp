package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.Quiz;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    TextView scoreText, answerText;
    Quiz[] quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        scoreText = (TextView)findViewById(R.id.quiz_score);
        answerText = (TextView)findViewById(R.id.question_answers);
        quiz = new Quiz[] {new Quiz()};
        setupView();
    }

    private void setupView() {
        String quizData = getIntent().getStringExtra("QUIZ");
        quiz[0] = new Gson().fromJson(quizData, (Type) Quiz.class);
        calculateScore();
        setAnswerView();
    }

    private void setAnswerView() {
        StringBuilder builder = new StringBuilder();
        Map<String,Question> questions = quiz[0].getQuestions();
        int i = 1;
        for (Map.Entry<String, Question> questionMap: quiz[0].questions.entrySet()){
            Question question = questionMap.getValue();
            builder.append("<font color='#18206F'> <b> Question: "+question.getDescription()+"</b> </font><br/><br/>");
            builder.append("<font color='#009688'> <b> Question: "+question.getAnswer()+"</b> </font><br/><br/>");
            }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            answerText.setText(Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT));
        }else{
            answerText.setText(Html.fromHtml(builder.toString()));
        }
    }

    private void calculateScore() {
        int score = 0;

        for (Map.Entry<String, Question> questionMap: quiz[0].questions.entrySet()){
            Question question = questionMap.getValue();
            if (question.getAnswer().equals(question.getUserAnswer())){
                score+=10;
            }
        }
        scoreText.setText("Your Score: "+ score);
    }
}