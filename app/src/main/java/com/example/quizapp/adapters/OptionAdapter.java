package com.example.quizapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.Question;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {
    public OptionAdapter(Question question, Context context) {
        this.question = question;
        this.context = context;
        options = new String[] {question.option1,question.option2,question.option3,question.option4};
    }

    private Question question;
    private String[] options;
    private Context context;
    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.option_layout,parent,false);
        return new OptionAdapter.OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        holder.optionView.setText(options[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.userAnswer = options[position];
                notifyDataSetChanged();
            }
        });

        if (question.userAnswer == options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_select_bg);
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg);
        }
    }

    @Override
    public int getItemCount() {
        return options.length;
    }

    public static class OptionViewHolder extends RecyclerView.ViewHolder {
        private TextView optionView;
        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionView = (TextView) itemView.findViewById(R.id.quiz_option);
        }
    }
}
