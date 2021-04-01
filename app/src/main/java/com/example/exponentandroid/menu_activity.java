package com.example.exponentandroid;

import android.app.Activity;
import android.os.Bundle;

import com.example.exponentandroid.fragments.PageFragmentAboutUs;
import com.example.exponentandroid.fragments.PageFragmentCameraListening;
import com.example.exponentandroid.fragments.PageFragmentCarInfo;
import com.example.exponentandroid.fragments.PageFragmentJPS;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragmentCameraListening());
        list.add(new PageFragmentJPS());
        list.add(new PageFragmentCarInfo());
        list.add(new PageFragmentAboutUs());

        pager = findViewById(R.id.pager);
        pagerAdapter = new PagerSwipeApdater(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(2);
    }

}