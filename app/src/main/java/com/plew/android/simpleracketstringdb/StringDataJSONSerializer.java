package com.plew.android.simpleracketstringdb;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Tim on 3/23/2015.
 */
public class StringDataJSONSerializer {
    private static final String TAG = "StringDataJSONSerializer";

    private Context mContext;
    private String mFilename;

    public StringDataJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public ArrayList<StringData> loadStringDatas() throws IOException, JSONException {
        //Log.d(TAG, "StringDataJSONSerializer(): calling loadStringDatas()");

        ArrayList<StringData> stringDatas = new ArrayList<StringData>();
        BufferedReader reader = null;
        try {
            // open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of StringDatas from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                stringDatas.add(new StringData(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return stringDatas;
    }

    public void saveStringDatas(ArrayList<StringData> stringDatas) throws JSONException, IOException {
        //Log.d(TAG, "StringDataJSONSerializer(): calling saveStringDatas()");

        // build an array in JSON
        JSONArray array = new JSONArray();
        for (StringData c : stringDatas)
            array.put(c.toJSON());

        // write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
