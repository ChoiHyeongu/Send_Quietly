package com.motivation.sendquiet.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motivation.sendquiet.R;
import com.motivation.sendquiet.adapter.MemberListAdapter;
import com.motivation.sendquiet.model.User;

import java.util.ArrayList;

public class MemberFragment extends Fragment {

    final String TAG = "MemberFragment";

    RecyclerView memberListView;

    private User loginUser;
    private ArrayList<User> users = new ArrayList<>();

    //Firebase
    private DatabaseReference userReference;

    public MemberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        memberListView = view.findViewById(R.id.member_list);

        Bundle bundle = getArguments();
        if(bundle != null){
            loginUser = (User)bundle.getSerializable("User");
            loginUser.getUserInfo("LoginUser");
        } else {
            Log.d("LoginUser", "Bundle is null");
        }

        setUserList();

        memberListView.setLayoutManager(new LinearLayoutManager(getContext()));
        MemberListAdapter memberListAdapter = new MemberListAdapter(users, loginUser);
        memberListView.setAdapter(memberListAdapter);
        return view;
    }

    private void setUserList(){

        userReference = FirebaseDatabase.getInstance().getReference("User");
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    users.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
