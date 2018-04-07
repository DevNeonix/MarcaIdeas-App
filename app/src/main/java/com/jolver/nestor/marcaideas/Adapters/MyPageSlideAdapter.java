package com.jolver.nestor.marcaideas.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Andre on 2/4/2018.
 */

public class MyPageSlideAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public MyPageSlideAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {

        return this.fragments.get(position);

    }

    @Override
    public int getCount() {

        return this.fragments.size();

    }


}
