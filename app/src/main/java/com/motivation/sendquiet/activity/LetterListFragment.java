package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motivation.sendquiet.R;
import com.motivation.sendquiet.adapter.LetterListAdapter;
import com.motivation.sendquiet.model.Letter;
import com.motivation.sendquiet.model.User;

import java.util.ArrayList;

public class LetterListFragment extends Fragment {

    final String TAG = "LetterListFragment";

    RecyclerView letterListView;

    private User loginUser;
    private ArrayList<Letter> letters = new ArrayList<>();

    //Firebase
    private DatabaseReference letterReference;

    public LetterListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letter_list, container, false);
        letterListView = view.findViewById(R.id.letter_list_view);

        Bundle bundle = getArguments();
        if(bundle != null){
            loginUser = (User)bundle.getSerializable("User");
            loginUser.getUserInfo("LoginUser");
        } else {
            Log.d("LoginUser", "Bundle is null");
        }

        setLetterList();

        letterListView.setLayoutManager(new LinearLayoutManager(getContext()));
        LetterListAdapter letterListAdapter = new LetterListAdapter(letters);
        letterListView.setAdapter(letterListAdapter);

        return view;
    }

    private void setLetterList(){

        letterReference = FirebaseDatabase.getInstance().getReference("Letter").child(loginUser.getId()).child("Receive");
        letterReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                letters.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Letter letter = snapshot.getValue(Letter.class);
                    letter.getLetter(TAG);
                    letters.add(letter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
