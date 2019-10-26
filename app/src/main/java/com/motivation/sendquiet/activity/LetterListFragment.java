package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.motivation.sendquiet.R;
import com.motivation.sendquiet.model.User;

public class LetterListFragment extends Fragment {

    final String TAG = "LetterListFragment";

    User user;

    public LetterListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            user = (User)bundle.getSerializable("User");
            user.getUserInfo(TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_letter_list, container, false);
        LinearLayout letter= view.findViewById(R.id.letter);
        letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLetter(v);
            }
        });
        return view;
    }

    public void onClickLetter(View view) {
        Intent intent = new Intent(getContext(), DetailActivitiy.class);
        getContext().startActivity(intent);
    }
}
