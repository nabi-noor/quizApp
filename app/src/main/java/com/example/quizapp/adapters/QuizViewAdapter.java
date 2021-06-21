package com.example.quizapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.activities.QuestionActivity;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.utils.ColorPicker;
import com.example.quizapp.utils.IconPicker;

import org.w3c.dom.Text;

import java.util.List;

public class QuizViewAdapter extends RecyclerView.Adapter<QuizViewAdapter.QuizViewHolder> {
    private final Context context;
    private final List<Quiz> quizzes;
    ColorPicker colorPicker;
    IconPicker iconPicker;
    public QuizViewAdapter(List<Quiz> quizzes, Context context)
    {
        this.quizzes = quizzes;
        this.context = context;
        this.colorPicker = new ColorPicker();
        this.iconPicker = new IconPicker();
    }
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.quiz_item,parent,false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.quizTitle.setText((quizzes.get(position).title));
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorPicker.colorPicker()));
        holder.quizIcon.setImageResource(iconPicker.getIcon());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuestionActivity.class);
            intent.putExtra("DATE", quizzes.get(position).title);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder{
        ImageView quizIcon;
        TextView quizTitle;
        CardView cardContainer;
        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            quizIcon = (ImageView) itemView.findViewById(R.id.quiz_icon);
            quizTitle = (TextView) itemView.findViewById(R.id.quiz_title);
            cardContainer = (CardView)itemView.findViewById(R.id.card_container);
        }
    }
}
