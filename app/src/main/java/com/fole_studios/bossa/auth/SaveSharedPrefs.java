package com.fole_studios.bossa.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPrefs
{
    public static final String LOGGED_IN_PREF = "logged_in_status";
    public static final String PHONE_NUMBER = "phone_number";

    static SharedPreferences getPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context, boolean loggedIn)
    {
        SharedPreferences.Editor _editor = getPreferences(context).edit();
        _editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        _editor.apply();
    }

    public static void setPhoneNumber(Context context, String phone)
    {
        SharedPreferences.Editor _editor = getPreferences(context).edit();
        _editor.putString(PHONE_NUMBER, phone);
        _editor.apply();
    }

    public static String getPhoneNumber(Context context)
    {
        return getPreferences(context).getString(PHONE_NUMBER, "");
    }

    public static boolean getLoggedStatus(Context context)
    {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }
}
