package com.example.quizapp.models;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz {


    public String id;

    public Quiz(String id, String title, Map<String, Question> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public String title;

    public Map<String, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Question> questions) {
        this.questions = questions;
    }

    public Map<String, Question> questions= Map.of();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Quiz() {
    }
}
