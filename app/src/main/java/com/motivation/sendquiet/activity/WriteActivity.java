package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.motivation.sendquiet.R;

public class WriteActivity extends AppCompatActivity {

    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        sendButton = findViewById(R.id.write_button_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteActivity.this, DetailActivitiy.class);
                startActivity(intent);
            }
        });
    }
}
