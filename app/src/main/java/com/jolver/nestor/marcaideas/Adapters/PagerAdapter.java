package com.jolver.nestor.marcaideas.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jolver.nestor.marcaideas.Fragments.ContactoLugarFragment;
import com.jolver.nestor.marcaideas.Fragments.InformacionLugarFragment;
import com.jolver.nestor.marcaideas.Fragments.LocationLugarFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;


    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new InformacionLugarFragment();
            case 1:
                return new ContactoLugarFragment();
            case 2:
                return new LocationLugarFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}