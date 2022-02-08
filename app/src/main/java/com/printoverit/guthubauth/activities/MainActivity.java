package com.printoverit.guthubauth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.printoverit.guthubauth.R;
import com.printoverit.guthubauth.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapter adapter;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.git_tabLayout);
       ViewPager2 pager2 = findViewById(R.id.project_viePager2);
        FragmentManager fm = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);
        name=getIntent().getStringExtra("user");

        tabLayout.addTab(tabLayout.newTab().setText("Repos"));
        tabLayout.addTab(tabLayout.newTab().setText("Starred"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
    public String getMyData() {
        return name;
    }
}