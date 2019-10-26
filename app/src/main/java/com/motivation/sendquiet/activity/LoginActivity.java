package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motivation.sendquiet.R;
import com.motivation.sendquiet.model.User;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    final String TAG = "LoginActivity";

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference userReference;
    private DatabaseReference reference;

    //View
    Button loginButton;
    EditText idEdit, passwordEdit;

    User user;

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

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        userReference = FirebaseDatabase.getInstance().getReference("User");
    }

    void singIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
           if(task.isSuccessful()){
               getUser(getUserId(email));
           } else {
               Toast.makeText(this, "회원정보에 없습니다.", Toast.LENGTH_LONG).show();
           }
        });
    }

    void getUser(String id){
        reference = userReference.child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    user.setId(id);
                    user.getUserInfo(TAG);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("User", user);
                    user.getUserInfo(TAG);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "회원 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
                Toast.makeText(getApplicationContext(), "예기치 못한 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    String getUserId(String email){
        int index = email.indexOf("@");
        return email.substring(0, index);
    }
}
