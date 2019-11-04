package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.motivation.sendquiet.R;
import com.motivation.sendquiet.model.Letter;
import com.motivation.sendquiet.model.User;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {

    final String TAG = "WriteActivity";

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference sendReference;
    private DatabaseReference receiveReference;
    private DatabaseReference toUserReference;
    private DatabaseReference fromUserReference;

    //User
    User toUser;
    User fromUser;

    //View
    EditText titleEdit, contentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        init();
    }

    public void sendLetter(View view) {
        Letter letter = createLetter();
        letter.getLetter(TAG);

        sendReference.child(letter.getTitle()).setValue(letter);
        receiveReference.child(letter.getTitle()).setValue(letter);

        //countToFrom();

        Toast.makeText(this, "정상적으로 전송되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void countToFrom(){
        toUserReference.setValue(toUser.getTo() + 1);
        fromUserReference.setValue(fromUser.getfrom() + 1);
    }


    private void init(){

        Intent getIntent = getIntent();
        ArrayList<User> userInfo = (ArrayList<User>) getIntent.getSerializableExtra("UserInfo");

        fromUser = userInfo.get(0);
        toUser = userInfo.get(1);

        titleEdit = findViewById(R.id.write_edit_title);
        contentEdit = findViewById(R.id.write_edit_content);

        sendReference = FirebaseDatabase.getInstance().
                            getReference("Letter").
                            child(fromUser.getId()).
                            child("Send");

        receiveReference = FirebaseDatabase.getInstance().
                            getReference("Letter").
                            child(toUser.getId()).
                            child("Receive");

        toUserReference = FirebaseDatabase.getInstance().
                            getReference("User").
                            child(toUser.getId()).
                            child("to");

        toUserReference = FirebaseDatabase.getInstance().
                            getReference("User").
                            child(fromUser.getId()).
                            child("from");
    }

    private Letter createLetter(){
        return new Letter(titleEdit.getText().toString(), contentEdit.getText().toString(), toUser.getName(), fromUser.getName());
    }
}
