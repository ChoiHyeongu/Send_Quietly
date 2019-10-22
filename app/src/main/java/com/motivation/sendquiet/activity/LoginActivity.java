package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.motivation.sendquiet.R;

public class LoginActivity extends AppCompatActivity {

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    //View
    Button loginButton;
    EditText idEdit, passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginButton.setOnClickListener(v -> singIn(idEdit.getText().toString(), passwordEdit.getText().toString()));
    }

    void init(){

        //View settings
        loginButton = findViewById(R.id.login_btn_login);
        idEdit = findViewById(R.id.login_edit_id);
        passwordEdit= findViewById(R.id.login_edit_password);
        idEdit.setHint("ID");
        passwordEdit.setHint("Password");

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    void singIn(String id, String password){
        mAuth.signInWithEmailAndPassword(id, password).addOnCompleteListener(this, task -> {
           if(task.isSuccessful()){
               FirebaseUser user = mAuth.getCurrentUser();
               Intent intent = new Intent(LoginActivity.this, MainActivity.class);

           } else {
               Toast.makeText(this, "회원정보에 없습니다.", Toast.LENGTH_LONG).show();
           }
        });
    }

    void makeUserInfo(String id){
        mDatabase.child("User").child(id);
    }
}
