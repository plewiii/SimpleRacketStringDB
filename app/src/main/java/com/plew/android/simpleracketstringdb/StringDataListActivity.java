package com.plew.android.simpleracketstringdb;

/**
 * Created by Tim on 3/4/2015.
 */
public class StringDataListActivity extends SingleFragmentActivity {

    @Override
    protected android.support.v4.app.Fragment createFragment() {
        return new StringDataListFragment();
    }
}
