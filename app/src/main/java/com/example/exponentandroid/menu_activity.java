package com.example.exponentandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.exponentandroid.fragments.PageFragmentAboutUs;
import com.example.exponentandroid.fragments.PageFragmentCameraListening;
import com.example.exponentandroid.fragments.PageFragmentCarInfo;
import com.example.exponentandroid.fragments.PageFragmentJPS;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class menu_activity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        pager = findViewById(R.id.pager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragmentCameraListening());
        list.add(new PageFragmentJPS());
        list.add(new PageFragmentCarInfo());
        list.add(new PageFragmentAboutUs());

        pagerAdapter = new PagerSwipeApdater(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(2);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigListener);

        final Handler handler = new Handler();
        final int delay = 100;
        handler.postDelayed(new Runnable() {
            public void run() {
                switch (pager.getCurrentItem()){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.cameras);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.jps);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.car_info);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.about_us);
                        break;
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.cameras:
                    pager.setCurrentItem(0);
//                    selectedFragment = new PageFragmentCameraListening();
                    break;
                case R.id.jps:
                    pager.setCurrentItem(1);
//                    selectedFragment = new PageFragmentJPS();
                    break;
                case R.id.car_info:
                    pager.setCurrentItem(2);
//                    selectedFragment = new PageFragmentCarInfo();
                    break;
                case R.id.about_us:
                    pager.setCurrentItem(3);
//                    selectedFragment = new PageFragmentAboutUs();
                    break;
            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,
//                    selectedFragment).commit();
            return true;
        }
    };

    public void OnClickSettings(View view){
        startActivity(new Intent(getApplicationContext(), settings_activity.class));
    }
}