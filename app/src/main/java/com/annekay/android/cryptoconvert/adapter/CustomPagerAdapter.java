package com.annekay.android.cryptoconvert.adapter;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.annekay.android.cryptoconvert.view.FeaturedCoinsFragment;
import com.annekay.android.cryptoconvert.view.RecentFragment;

import static android.net.wifi.p2p.nsd.WifiP2pServiceRequest.newInstance;

/**
 * Created by annekay on 10/26/2017.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Featured", "Recent" };

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FeaturedCoinsFragment();


        }else {
            return new RecentFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
