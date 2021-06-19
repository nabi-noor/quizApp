package com.example.quizapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.quizapp.R;
import com.example.quizapp.adapters.QuizViewAdapter;
import com.example.quizapp.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar appBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout mainDrawer;
    private List<Quiz> quizzes = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDrawer = (DrawerLayout)findViewById(R.id.main_drawer);
        appBar =(Toolbar)findViewById(R.id.top_bar);
        recyclerView = (RecyclerView)findViewById(R.id.quiz_recycler_view);
        pupulateDummy();
        setupView();

    }

    private void setupView(){

        setupDrawer();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new QuizViewAdapter(quizzes));
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

    public void pupulateDummy(){
        quizzes.add(new Quiz("1","Sign"));
        quizzes.add(new Quiz("2","Sign Now"));
        quizzes.add(new Quiz("3","Sign Later"));
        quizzes.add(new Quiz("4","Sign Tomorrow"));
    }
}