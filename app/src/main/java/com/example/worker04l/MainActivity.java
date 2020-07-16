package com.example.worker04l;

import android.os.Bundle;

import com.example.worker04l.adapter.Myadapter;
import com.example.worker04l.fragment.Fragment2;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> list;
    private Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        myadapter = new Myadapter(getSupportFragmentManager(), 0, list);
        vp.setAdapter(myadapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.ic_launcher_background);
        tab.getTabAt(1).setText("我的").setIcon(R.drawable.ic_launcher_background);
    }
}
