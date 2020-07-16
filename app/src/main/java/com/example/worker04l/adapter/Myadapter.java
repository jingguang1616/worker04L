package com.example.worker04l.adapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Myadapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> list;

    public Myadapter(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> list) {
        super(fm, behavior);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
