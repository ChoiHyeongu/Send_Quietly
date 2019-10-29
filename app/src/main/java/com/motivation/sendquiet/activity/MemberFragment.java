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

    private User user;
    private ArrayList<User> users = new ArrayList<>();
    private MemberListAdapter memberListAdapter;

    //Firebase
    private DatabaseReference userReference;
    private DatabaseReference reference;

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
        memberListView = view.findViewById(R.id.member_list);

        userReference = FirebaseDatabase.getInstance().getReference("User");
        users = setUserList();

        for(int i = 0; i < users.size(); i++){
            Log.d("onCV", users.get(i).getName());
        }

        memberListView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private ArrayList<User> setUserList(){

        ArrayList<User> list = new ArrayList<>();

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    list.add(user);
                    Log.d("onDataChange", user.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d("setUserLIst", String.valueOf(list.size()));
        return list;
    }
}
