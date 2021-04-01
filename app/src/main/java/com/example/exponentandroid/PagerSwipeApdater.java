package com.example.exponentandroid;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class PagerSwipeApdater extends FragmentStatePagerAdapter {
    private List<Fragment> fragment_list;

    public PagerSwipeApdater(@NonNull FragmentManager fm, List<Fragment> fragment_list) {
        super(fm);
        this.fragment_list = fragment_list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }
}
