package com.motivation.sendquiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.motivation.sendquiet.R;
import com.motivation.sendquiet.activity.WriteActivity;
import com.motivation.sendquiet.model.Letter;
import com.motivation.sendquiet.model.User;

import java.util.ArrayList;

public class LetterListAdapter extends RecyclerView.Adapter<LetterListAdapter.ViewHolder> {

    private ArrayList<Letter> letterList;
    Context context;

    public LetterListAdapter(ArrayList<Letter> letterList) {
        this.letterList = letterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_letter, viewGroup, false);
        return new LetterListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Letter letter = letterList.get(i);

        viewHolder.titleText.setText(letter.getTitle());
        viewHolder.contentText.setText(letter.getContent());
        viewHolder.titleText.setOnClickListener(v -> {
        });
    }

    @Override
    public int getItemCount() {
        return letterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titleText, contentText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.letter_text_title);
            contentText = itemView.findViewById(R.id.letter_text_content);
        }
    }
}
