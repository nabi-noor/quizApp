package com.example.quizapp.models;

public class Quiz {
    public Quiz(String id, String title, Question[] questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    private String id;

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

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    private String title;
    private Question questions[];
}
