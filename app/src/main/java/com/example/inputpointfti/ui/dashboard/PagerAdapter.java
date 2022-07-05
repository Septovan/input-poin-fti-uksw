package com.example.inputpointfti.ui.dashboard;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private Context context;

    public PagerAdapter(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        this.context = context;
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LihatPoinFragment();
            case 1:
                return new InputPoinFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
