package com.motivation.sendquiet.activity;

import android.content.Intent;
import android.net.wifi.p2p.nsd.WifiP2pServiceRequest;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.motivation.sendquiet.R;

public class MainActivity extends AppCompatActivity {

    MemberFragment memberFragment;
    LetterListFragment letterListFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_member:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container, memberFragment).commit();
                    break;
                case R.id.navigation_letter:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container, letterListFragment).commit();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memberFragment = new MemberFragment();
        letterListFragment = new LetterListFragment();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, memberFragment).commit();
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(MainActivity.this, WriteActivity.class);
        startActivity(intent);
    }
}