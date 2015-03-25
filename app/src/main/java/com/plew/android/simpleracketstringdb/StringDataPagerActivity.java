package com.plew.android.simpleracketstringdb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Tim on 3/5/2015.
 */
public class StringDataPagerActivity extends ActionBarActivity {  // Peter: FragmentActivity

    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        final ArrayList<StringData> mStringDatas = StringDataLab.get(this).getStringDatas();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return mStringDatas.size();
            }
            @Override
            public Fragment getItem(int pos) {
                UUID stringdataId =  mStringDatas.get(pos).getId();
                return StringDataFragment.newInstance(stringdataId);
            }
        });

        UUID crimeId = (UUID)getIntent().getSerializableExtra(StringDataFragment.EXTRA_STRINGDATA_ID);
        for (int i = 0; i < mStringDatas.size(); i++) {
            if (mStringDatas.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        // Peter: mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        // Peter:     public void onPageScrollStateChanged(int state) {}

        // Peter:     public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {}

        // Peter:     public void onPageSelected(int pos){
        // Peter:         StringData stringdata = mStringDatas.get(pos);
        // Peter:         if (stringdata.getName() != null) {
        // Peter:             setTitle(stringdata.getName());
        // Peter:         }
        // Peter:     }
        // Peter: });
    }
}
