package com.plew.android.simpleracketstringdb;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StringDataListFragment extends ListFragment {  // Orig: Fragment


    public StringDataListFragment() {
        // Required empty public constructor
    }


    // Orig: @Override
    // Orig: public View onCreateView(LayoutInflater inflater, ViewGroup container,
    // Orig:                          Bundle savedInstanceState) {
    // Orig:     TextView textView = new TextView(getActivity());
    // Orig:     textView.setText(R.string.hello_blank_fragment);
    // Orig:     return textView;
    // Orig: }

    private static final String TAG = "StringDataListFragment";

    private ArrayList<StringData> mStringDatas;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.strings_title);
        mStringDatas = StringDataLab.get(getActivity()).getStringDatas();
        StringDataAdapter adapter = new StringDataAdapter(mStringDatas);
        setListAdapter(adapter);

        // Peter: needs menu-v11: setRetainInstance(true);
        // Peter: needs menu-v11: mSubtitleVisible = false;
    }

    //@TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        // Peter: needs menu-v11: if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        // Peter: needs menu-v11:     if (mSubtitleVisible) {
        // Peter: needs menu-v11:         getActivity().getActionBar().setSubtitle(R.string.subtitle);
        // Peter: needs menu-v11:     }
        // Peter: needs menu-v11: }

        ListView listView = (ListView)v.findViewById(android.R.id.list);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            registerForContextMenu(listView);
        } else {
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.stringdata_list_item_context, menu);
                    return true;
                }

                public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                      long id, boolean checked) {
                }

                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_item_delete_stringdata:
                            StringDataAdapter adapter = (StringDataAdapter) getListAdapter();
                            StringDataLab crimeLab = StringDataLab.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    crimeLab.deleteCrime(adapter.getItem(i));
                                }
                            }
                            mode.finish();
                            adapter.notifyDataSetChanged();
                            return true;
                        default:
                            return false;
                    }
                }

                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                public void onDestroyActionMode(ActionMode mode) {

                }
            });

        }

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // get the StringData from the adapter
        StringData c = (StringData)(getListAdapter()).getItem(position);
        //Log.d(TAG, c.getName() + " was clicked");

        // delete: // start an instance of StringDataActivity
        // delete: Intent i = new Intent(getActivity(), StringDataActivity.class);
        // start an instance of StringDataPagerActivity
        Intent i = new Intent(getActivity(), StringDataPagerActivity.class);
        i.putExtra(StringDataFragment.EXTRA_STRINGDATA_ID, c.getId());
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((StringDataAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_stringdata_list, menu);
        //MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
        //if (mSubtitleVisible && showSubtitle != null) {
        //    showSubtitle.setTitle(R.string.hide_subtitle);
        //}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_stringdata:
                StringData stringdata = new StringData();
                StringDataLab.get(getActivity()).addStringData(stringdata);
                Intent i = new Intent(getActivity(), StringDataActivity.class);
                i.putExtra(StringDataFragment.EXTRA_STRINGDATA_ID, stringdata.getId());
                startActivityForResult(i, 0);
                return true;
            // Peter: needs menu-v11: case R.id.menu_item_show_subtitle:
            // Peter: needs menu-v11:     if (getActivity().getActionBar().getSubtitle() == null) {
            // Peter: needs menu-v11:         getActivity().getActionBar().setSubtitle(R.string.subtitle);
            // Peter: needs menu-v11:         mSubtitleVisible = true;
            // Peter: needs menu-v11:         item.setTitle(R.string.hide_subtitle);
            // Peter: needs menu-v11:      }  else {
            // Peter: needs menu-v11:         getActivity().getActionBar().setSubtitle(null);
            // Peter: needs menu-v11:         mSubtitleVisible = false;
            // Peter: needs menu-v11:         item.setTitle(R.string.show_subtitle);
            // Peter: needs menu-v11:     }
            // Peter: needs menu-v11:     return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.stringdata_list_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;
        StringDataAdapter adapter = (StringDataAdapter)getListAdapter();
        StringData stringData = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_stringdata:
                StringDataLab.get(getActivity()).deleteCrime(stringData);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private class StringDataAdapter extends ArrayAdapter<StringData> {
        public StringDataAdapter(ArrayList<StringData> StringDatas) {
            super(getActivity(), android.R.layout.simple_list_item_1, StringDatas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_stringdata, null);
            }

            // configure the view for this StringData
            StringData c = getItem(position);

            TextView mainStringTextView =
                    (TextView)convertView.findViewById(R.id.stringdata_list_item_mainStringTextView);
            mainStringTextView.setText("Mains: " + c.getMainName() + " " + c.getMainGauge() + " @ "
                    + c.getMainTension() + " " + c.getMainTensionUnits());
            TextView crossStringTextView =
                    (TextView)convertView.findViewById(R.id.stringdata_list_item_crossStringTextView);
            crossStringTextView.setText("Crosses: " + c.getCrossName() + " " + c.getCrossGauge() + " @ "
                    + c.getCrossTension() + " " + c.getCrossTensionUnits());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.stringdata_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());

            return convertView;
        }
    }
}
