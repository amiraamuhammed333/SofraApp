package com.example.sofraapp.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();
    List<String> fragmentsTitle = new ArrayList<>();

    public TabAdapter(FragmentManager fm) {
        super ( fm );
    }

    public void addPage(Fragment fragment, String title) {
        fragments.add ( fragment );
        fragmentsTitle.add ( title );
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {

        return fragments.get ( position );

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitle.get ( position );
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return fragments.size ();
    }
}
