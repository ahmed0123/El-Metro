package com.example.hendawy.metro.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class StationManger {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "StationSaver";

    // All Shared Preferences Keys
    // User name (make variable public to access from outside)
    public static final String KEY_FIRST_VALUE = "CurrentStationNumber";

    public static final String KEY_FIRST_NAME = "CurrentStationName";

    // Email address (make variable public to access from outside)
    public static final String KEY_STATE = "IsUserSaveCurrentSession";

    // Constructor
    public StationManger(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void isUserSaveCurrentSession(boolean state) {

        editor.putBoolean(KEY_STATE, state);
        editor.commit();
    }
    public boolean getUserState() {

        return pref.getBoolean(KEY_STATE,false);
    }

    public void setCurrentStationNumber(int stationNo) {

        editor.putInt(KEY_FIRST_VALUE, stationNo);
        editor.commit();
    }
    public void setCurrentStationName(String name) {

        editor.putString(KEY_FIRST_NAME, name);
        editor.commit();
    }


    public int getCurrentStationNumber() {

        return pref.getInt(KEY_FIRST_VALUE, 0);
    }
    public String getCurrentStationName() {

        return pref.getString(KEY_FIRST_NAME, "");
    }
    public void removeCurrentStationNumber() {
        editor.remove(KEY_FIRST_VALUE);
        editor.commit();
    }
    public void removeCurrentStationName(){
        editor.remove(KEY_FIRST_NAME);
        editor.commit();
    }

}
