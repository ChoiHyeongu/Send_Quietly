package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.motivation.sendquiet.R;
import com.motivation.sendquiet.model.User;

public class WriteActivity extends AppCompatActivity {

    Button sendButton;
    final String TAG = "WriteActivity";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getSelectedUserInfo();

        sendButton = findViewById(R.id.write_button_send);
        sendButton.setOnClickListener(v -> {
            Intent intent = new Intent(WriteActivity.this, DetailActivitiy.class);
            startActivity(intent);
        });
    }

    private void getSelectedUserInfo(){
        Intent getIntent = getIntent();
        User user = (User) getIntent.getSerializableExtra("UserInfo");
        user.getUserInfo(TAG);
    }
}
