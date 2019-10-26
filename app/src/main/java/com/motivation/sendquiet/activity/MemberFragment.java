package com.motivation.sendquiet.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motivation.sendquiet.R;
import com.motivation.sendquiet.model.User;

public class MemberFragment extends Fragment {

    final String TAG = "MemberFragment";

    User user;

    public MemberFragment() {
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
        View view = inflater.inflate(R.layout.fragment_member, container, false);

        return view;
    }


}
