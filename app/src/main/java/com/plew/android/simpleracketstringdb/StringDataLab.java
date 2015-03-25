package com.plew.android.simpleracketstringdb;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Tim on 3/4/2015.
 */
public class StringDataLab {
    private static final String TAG = "StringDataLab";
    private static final String FILENAME = "stringdatas.json";

    private ArrayList<StringData> mStringDatas;
    private StringDataJSONSerializer mSerializer;

    private static StringDataLab sStringDataLab;
    private Context mAppContext;

    private StringDataLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new StringDataJSONSerializer(mAppContext, FILENAME);

        try {
            mStringDatas = mSerializer.loadStringDatas();
        } catch (Exception e) {
            mStringDatas = new ArrayList<StringData>();
            Log.e(TAG, "Error loading stringdatas: ", e);
        }
    }

    public static StringDataLab get(Context c) {
        if (sStringDataLab == null) {
            sStringDataLab = new StringDataLab(c.getApplicationContext());
        }
        return sStringDataLab;
    }

    public StringData getCrime(UUID id) {
        for (StringData c : mStringDatas) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void addStringData(StringData c) {
        mStringDatas.add(c);
        saveStringDatas();
    }

    public ArrayList<StringData> getStringDatas() {
        return mStringDatas;
    }

    public void deleteStringData(StringData c) {
        mStringDatas.remove(c);
        saveStringDatas();
    }

    public boolean saveStringDatas() {
        try {
            mSerializer.saveStringDatas(mStringDatas);
            //Log.d(TAG, "stringdatas saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving stringdatas: " + e);
            return false;
        }
    }

    public void deleteCrime(StringData c) {
        mStringDatas.remove(c);
    }
}
