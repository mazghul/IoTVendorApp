package com.smartcard.iotaspirantsvendor;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.app.Fragment;


import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment fragment = AddProductFragment.newInstance();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    Fragment fragment2 = ListFragment.newInstance();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment2).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getSelectedItemId());
        navView.setItemBackground(getDrawable(R.color.bg));
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment fragment = AddProductFragment.newInstance();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

}
