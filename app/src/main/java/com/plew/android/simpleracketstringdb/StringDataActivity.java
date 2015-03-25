package com.plew.android.simpleracketstringdb;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

public class StringDataActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        // delete: return new StringDataFragment();

        UUID crimeId = (UUID)getIntent()
                .getSerializableExtra(StringDataFragment.EXTRA_STRINGDATA_ID);
        return StringDataFragment.newInstance(crimeId);
    }
}


// Peter: old version before SingleFragmentActivity */
/*
public class StringDataActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        // Orig: if (savedInstanceState == null) {
            // Orig: getSupportFragmentManager().beginTransaction()
            // Orig:         .add(R.id.container, new PlaceholderFragment())
            // Orig:         .commit();
        // Orig: }

        // Peter: code above did not work.  Following from CrimeActivity.java, chapter 8
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new StringDataFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

    }


    // Orig: @Override
    // Orig: public boolean onCreateOptionsMenu(Menu menu) {
    // Orig:     // Inflate the menu; this adds items to the action bar if it is present.
    // Orig:     getMenuInflater().inflate(R.menu.peter_menu_string, menu);
    // Orig:     return true;
    // Orig: }

    // Orig: @Override
    // Orig: public boolean onOptionsItemSelected(MenuItem item) {
    // Orig:     // Handle action bar item clicks here. The action bar will
    // Orig:     // automatically handle clicks on the Home/Up button, so long
    // Orig:     // as you specify a parent activity in AndroidManifest.xml.
    // Orig:     int id = item.getItemId();

    // Orig:     //noinspection SimplifiableIfStatement
    // Orig:     if (id == R.id.action_settings) {
    // Orig:         return true;
    // Orig:     }

    // Orig:     return super.onOptionsItemSelected(item);
    // Orig: }

    // Orig: /**
    // Orig:  * A placeholder fragment containing a simple view.
    // Orig:  */
// Peter: old version before SingleFragmentActivity */
/* Peter
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Original: View rootView = inflater.inflate(R.layout.peter_tbd_fragment_string, container, false);
            View rootView = inflater.inflate(R.layout.fragment_stringdata, container, false);

            return rootView;
        }
    }
}
Peter */
