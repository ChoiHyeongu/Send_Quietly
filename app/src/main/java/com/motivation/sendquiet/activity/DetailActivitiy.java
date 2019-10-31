package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.motivation.sendquiet.R;
import com.motivation.sendquiet.model.Letter;

public class DetailActivitiy extends AppCompatActivity {

    final String TAG = "DetailActivity";

    TextView titleText;
    TextView contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent getIntent = getIntent();
        Letter letter = (Letter) getIntent.getSerializableExtra("Letter");
        letter.getLetter(TAG);

        titleText = findViewById(R.id.detail_text_title);
        contentText = findViewById(R.id.detail_text_content);

        titleText.setText(letter.getTitle());
        contentText.setText(letter.getContent());
    }
}
