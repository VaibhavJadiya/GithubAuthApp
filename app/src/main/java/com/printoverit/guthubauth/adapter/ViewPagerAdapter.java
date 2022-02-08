package com.printoverit.guthubauth.adapter;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.printoverit.guthubauth.fragments.ProjectListtFragment;
import com.printoverit.guthubauth.fragments.StaredProjectsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0 :
                return new ProjectListtFragment();
            case 1 :
                return new StaredProjectsFragment();
        }

        return new ProjectListtFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle){
        super(fragmentManager,lifecycle);

    }
}
