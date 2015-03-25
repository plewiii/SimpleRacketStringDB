package com.plew.android.simpleracketstringdb;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Tim on 2/26/2015.
 */
public class StringData {

    private static final String TAG = "StringData";

    private static final String JSON_ID = "id";
    private static final String JSON_DATE = "date";
    private static final String JSON_MAIN_NAME = "main_name";
    private static final String JSON_MAIN_GAUGE = "main_gauge";
    private static final String JSON_MAIN_TENSION = "main_tension";
    private static final String JSON_MAIN_TENSION_UNITS = "main_tension_units";
    private static final String JSON_MAIN_PRESTRETCH = "main_prestretch";
    private static final String JSON_CROSS_NAME = "cross_name";
    private static final String JSON_CROSS_GAUGE = "cross_gauge";
    private static final String JSON_CROSS_TENSION = "cross_tension";
    private static final String JSON_CROSS_TENSION_UNITS = "cross_tension_units";
    private static final String JSON_CROSS_PRESTRETCH = "cross_prestretch";
    private static final String JSON_COMMENTS = "comments";

    private UUID mId;
    private Date mDate;

    private String mMainName;
    private String mMainGauge;
    private String mMainTension;
    private String mMainTensionUnits;
    private boolean mMainPrestretch;
    private String mCrossName;
    private String mCrossGauge;
    private String mCrossTension;
    private String mCrossTensionUnits;
    private boolean mCrossPrestretch;

    private String mComments;

    public StringData() {
        mId = UUID.randomUUID();
        mDate = new Date();
        //Log.d(TAG, "StringData(): " + mDate);

        mMainName = "Main String Mfg/Model";
        mMainGauge = "16";
        mMainTension = "55";
        mMainTensionUnits = "lbs";
        mMainPrestretch = false;

        mCrossName = "Crosses String Mfg/Model";
        mCrossGauge = "16";
        mCrossTension = "55";
        mCrossTensionUnits = "lbs";
        mCrossPrestretch = false;

        mComments = "None";
    }

    public StringData(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mDate = new Date(json.getLong(JSON_DATE));

        mMainName = json.getString(JSON_MAIN_NAME);
        mMainGauge = json.getString(JSON_MAIN_GAUGE);
        mMainTension = json.getString(JSON_MAIN_TENSION);
        mMainTensionUnits = json.getString(JSON_MAIN_TENSION_UNITS);
        mMainPrestretch = json.getBoolean(JSON_MAIN_PRESTRETCH);

        mCrossName = json.getString(JSON_CROSS_NAME);
        mCrossGauge = json.getString(JSON_CROSS_GAUGE);
        mCrossTension = json.getString(JSON_CROSS_TENSION);
        mCrossTensionUnits = json.getString(JSON_CROSS_TENSION_UNITS);
        mCrossPrestretch = json.getBoolean(JSON_CROSS_PRESTRETCH);

        mComments = json.getString(JSON_COMMENTS);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(JSON_ID, mId.toString());
        json.put(JSON_DATE, mDate.getTime());

        json.put(JSON_MAIN_NAME, mMainName);
        json.put(JSON_MAIN_GAUGE, mMainGauge);
        json.put(JSON_MAIN_TENSION, mMainTension);
        json.put(JSON_MAIN_TENSION_UNITS, mMainTensionUnits);
        json.put(JSON_MAIN_PRESTRETCH, mMainPrestretch);

        json.put(JSON_CROSS_NAME, mCrossName);
        json.put(JSON_CROSS_GAUGE, mCrossGauge);
        json.put(JSON_CROSS_TENSION, mCrossTension);
        json.put(JSON_CROSS_TENSION_UNITS, mCrossTensionUnits);
        json.put(JSON_CROSS_PRESTRETCH, mCrossPrestretch);

        json.put(JSON_COMMENTS, mComments);

        return json;
    }

    //@Override
    //public String toString() {
    //    return mMainName;
    //}

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        //Log.d(TAG, "getDate(): " + mDate);
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getMainName() {
        //Log.d(TAG, "getMainName(): " + mMainName);
        return mMainName;
    }

    public void setMainName(String mainName) {
        mMainName = mainName;
        //Log.d(TAG, "setMainName(): " + mMainName);
    }

    public String getMainGauge() {
        return mMainGauge;
    }

    public void setMainGauge(String mainGauge) { mMainGauge = mainGauge; }

    public String getMainTension() {
        return mMainTension;
    }

    public void setMainTension(String mainTension) {
        mMainTension = mainTension;
    }

    public String getMainTensionUnits() {
        return mMainTensionUnits;
    }

    public void setMainTensionUnits(String mainTensionUnits) {
        mMainTensionUnits = mainTensionUnits;
    }

    public boolean isMainPrestretch() {
        return mMainPrestretch;
    }

    public void setMainPrestretch(boolean mainPrestretch) { mMainPrestretch = mainPrestretch; }

    public String getCrossName() {
        //Log.d(TAG, "getCrossName(): " + mCrossName);
        return mCrossName;
    }

    public void setCrossName(String crossName) {
        mCrossName = crossName;
        //Log.d(TAG, "setCrossName(): " + mCrossName);
    }

    public String getCrossGauge() {
        return mCrossGauge;
    }

    public void setCrossGauge(String crossGauge) { mCrossGauge = crossGauge; }

    public String getCrossTension() {
        return mCrossTension;
    }

    public void setCrossTension(String crossTension) { mCrossTension = crossTension; }

    public String getCrossTensionUnits() {
        return mCrossTensionUnits;
    }

    public void setCrossTensionUnits(String crossTensionUnits) {
        mCrossTensionUnits = crossTensionUnits;
    }

    public boolean isCrossPrestretch() {
        return mCrossPrestretch;
    }

    public void setCrossPrestretch(boolean crossPrestretch) { mCrossPrestretch = crossPrestretch; }

    public String getComments() {
        return mComments;
    }

    public void setComments(String comments) { mComments = comments; }

}
