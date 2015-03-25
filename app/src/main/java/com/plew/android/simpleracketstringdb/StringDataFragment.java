package com.plew.android.simpleracketstringdb;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StringDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StringDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StringDataFragment extends Fragment {

    private static final String TAG = "StringDataFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // Orig: private static final String ARG_PARAM1 = "param1";
    // Orig: private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    // Orig: private String mParam1;
    // Orig: private String mParam2;

    // Orig: private OnFragmentInteractionListener mListener;

    public static final String EXTRA_STRINGDATA_ID = "stringdataintent.STRINGDATA_ID";

    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;

    StringData mStringData;
    EditText mMainNameField;
    EditText mMainGaugeField;
    EditText mMainTensionField;
    RadioButton mMainTensionUnitslbsRadioButton;
    RadioButton mMainTensionUnitskgRadioButton;
    CheckBox mMainPrestretchCheckBox;
    EditText mCrossNameField;
    EditText mCrossGaugeField;
    EditText mCrossTensionField;
    RadioButton mCrossTensionUnitslbsRadioButton;
    RadioButton mCrossTensionUnitskgRadioButton;
    CheckBox mCrossPrestretchCheckBox;
    Button mDateButton;
    EditText mCommentsField;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StringDataFragment.
     */
    // Orig: // TODO: Rename and change types and number of parameters
    // Orig: public static StringDataFragment newInstance(String param1, String param2) {
    // Orig:     Log.d(TAG, "newInstance(): ");

    // Orig:     StringDataFragment fragment = new StringDataFragment();
    // Orig:     Bundle args = new Bundle();
    // Orig:     args.putString(ARG_PARAM1, param1);
    // Orig:     args.putString(ARG_PARAM2, param2);
    // Orig:     fragment.setArguments(args);
    // Orig:     return fragment;
    // Orig: }

    public StringDataFragment() {
        // Required empty public constructor

        //Log.d(TAG, "StringDataFragment(): ");
    }

    public static StringDataFragment newInstance(UUID stringdataId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_STRINGDATA_ID, stringdataId);

        StringDataFragment fragment = new StringDataFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Orig: if (getArguments() != null) {
        // Orig:     mParam1 = getArguments().getString(ARG_PARAM1);
        // Orig:     mParam2 = getArguments().getString(ARG_PARAM2);
        // Orig: }

        //Log.d(TAG, "onCreate(): ");
        // delete: mStringData = new StringData();
        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_STRINGDATA_ID);
        mStringData = StringDataLab.get(getActivity()).getCrime(crimeId);

        setHasOptionsMenu(true);
    }

    public void updateDate() {
        mDateButton.setText(mStringData.getDate().toString());
    }

    // delete: BNR forum: @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_stringdata, container, false);

        //Log.d(TAG, "onCreateView(): ");
        View v = inflater.inflate(R.layout.fragment_stringdata, container, false);

        // delete: BNR forum: if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        // delete: BNR forum:     if (NavUtils.getParentActivityName(getActivity()) != null) {
        // delete: BNR forum:         getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        // delete: BNR forum:     }
        // delete: BNR forum: }

        // Following is based on BNR forum:
        if (NavUtils.getParentActivityName(getActivity()) != null) {
             ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mMainNameField = (EditText)v.findViewById(R.id.stringdata_main_name);
        mMainNameField.setText(mStringData.getMainName());
        mMainNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringData.setMainName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mMainGaugeField = (EditText)v.findViewById(R.id.stringdata_main_gauge);
        mMainGaugeField.setText(mStringData.getMainGauge());
        mMainGaugeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringData.setMainGauge(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mMainTensionField = (EditText)v.findViewById(R.id.stringdata_main_tension);
        mMainTensionField.setText(mStringData.getMainTension());
        mMainTensionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringData.setMainTension(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mMainTensionUnitslbsRadioButton = (RadioButton)v.findViewById(R.id.stringdata_main_tension_units_lbs);
        if (mStringData.getMainTensionUnits() == "lbs") {
            mMainTensionUnitslbsRadioButton.setChecked(true);
        }
        else {
            mMainTensionUnitslbsRadioButton.setChecked(false);
        }
        mMainTensionUnitslbsRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMainTensionUnitslbsRadioButton.isChecked()) {
                    mStringData.setMainTensionUnits("lbs");
                    //Log.d(TAG, "StringDataFragment(): main: lbs");
                }
            }
        });

        mMainTensionUnitskgRadioButton = (RadioButton)v.findViewById(R.id.stringdata_main_tension_units_kg);
        if (mStringData.getMainTensionUnits() == "kg") {
            mMainTensionUnitskgRadioButton.setChecked(true);
        }
        else {
            mMainTensionUnitskgRadioButton.setChecked(false);
        }
        mMainTensionUnitskgRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMainTensionUnitskgRadioButton.isChecked()) {
                    mStringData.setMainTensionUnits("kg");
                    //Log.d(TAG, "StringDataFragment(): main: kg");
                }
            }
        });

        mMainPrestretchCheckBox = (CheckBox)v.findViewById(R.id.stringdata_main_prestretch);
        mMainPrestretchCheckBox.setChecked(mStringData.isMainPrestretch());
        mMainPrestretchCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // set the stringdata's prestretch property
            mStringData.setMainPrestretch(isChecked);
            }
        });

        mCrossNameField = (EditText)v.findViewById(R.id.stringdata_cross_name);
        mCrossNameField.setText(mStringData.getCrossName());
        mCrossNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringData.setCrossName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCrossGaugeField = (EditText)v.findViewById(R.id.stringdata_cross_gauge);
        mCrossGaugeField.setText(mStringData.getCrossGauge());
        mCrossGaugeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringData.setCrossGauge(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCrossTensionField = (EditText)v.findViewById(R.id.stringdata_cross_tension);
        mCrossTensionField.setText(mStringData.getCrossTension());
        mCrossTensionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringData.setCrossTension(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCrossTensionUnitslbsRadioButton = (RadioButton)v.findViewById(R.id.stringdata_cross_tension_units_lbs);
        if (mStringData.getCrossTensionUnits() == "lbs") {
            mCrossTensionUnitslbsRadioButton.setChecked(true);
        }
        else {
            mCrossTensionUnitslbsRadioButton.setChecked(false);
        }
        mCrossTensionUnitslbsRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCrossTensionUnitslbsRadioButton.isChecked()) {
                    mStringData.setCrossTensionUnits("lbs");
                    //Log.d(TAG, "StringDataFragment(): cross: lbs");
                }
            }
        });

        mCrossTensionUnitskgRadioButton = (RadioButton)v.findViewById(R.id.stringdata_cross_tension_units_kg);
        if (mStringData.getCrossTensionUnits() == "kg") {
            mCrossTensionUnitskgRadioButton.setChecked(true);
        }
        else {
            mCrossTensionUnitskgRadioButton.setChecked(false);
        }
        mCrossTensionUnitskgRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCrossTensionUnitskgRadioButton.isChecked()) {
                    mStringData.setCrossTensionUnits("kg");
                    //Log.d(TAG, "StringDataFragment(): cross: kg");
                }
            }
        });

        mCrossPrestretchCheckBox = (CheckBox)v.findViewById(R.id.stringdata_cross_prestretch);
        mCrossPrestretchCheckBox.setChecked(mStringData.isCrossPrestretch());
        mCrossPrestretchCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // set the stringdata's prestretch property
            mStringData.setCrossPrestretch(isChecked);
            }
        });

        mDateButton = (Button)v.findViewById(R.id.stringdata_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mStringData.getDate());
                dialog.setTargetFragment(StringDataFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mCommentsField = (EditText)v.findViewById(R.id.stringdata_comments);
        mCommentsField.setText(mStringData.getComments());
        mCommentsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringData.setComments(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mStringData.setDate(date);
            updateDate();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        StringDataLab.get(getActivity()).saveStringDatas();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "onOptionsItemSelected: 1. android.R.id.home");
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    Log.d(TAG, "onOptionsItemSelected: 2. android.R.id.home");
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Orig: // TODO: Rename method, update argument and hook method into UI event
    // Orig: public void onButtonPressed(Uri uri) {
    // Orig:     if (mListener != null) {
    // Orig:         mListener.onFragmentInteraction(uri);
    // Orig:     }
    // Orig: }

    // Orig: @Override
    // Orig: public void onAttach(Activity activity) {
    // Orig:     super.onAttach(activity);
    // Orig:     try {
    // Orig:         mListener = (OnFragmentInteractionListener) activity;
    // Orig:     } catch (ClassCastException e) {
    // Orig:         throw new ClassCastException(activity.toString()
    // Orig:                 + " must implement OnFragmentInteractionListener");
    // Orig:     }
    // Orig: }

    // Orig: @Override
    // Orig: public void onDetach() {
    // Orig:     super.onDetach();
    // Orig:     mListener = null;
    // Orig: }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    // Orig: public interface OnFragmentInteractionListener {
    // Orig:     // TODO: Update argument type and name
    // Orig:     public void onFragmentInteraction(Uri uri);
    // Orig: }

}
