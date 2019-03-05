package com.techeasesol.myface.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.techeasesol.myface.R;

public class GeneralUtils {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static Fragment connectFragment(Context context,Fragment fragment){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("true").commit();
        return fragment;
    }

    public static Fragment connectFragmentWithoutBack(Context context,Fragment fragment){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        return fragment;
    }

    public static Fragment connectDrawerFragment(Context context,Fragment fragment){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.drawer_container,fragment).addToBackStack("true").commit();
        return fragment;
    }

    public static Fragment connectDrawerFragmentWithoutBack(Context context,Fragment fragment){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.drawer_container,fragment).commit();
        return fragment;
    }

    public static SharedPreferences.Editor putStringValueInEditor(Context context, String key, String value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(key, value).commit();
        return editor;
    }

    public static SharedPreferences.Editor putIntegerValueInEditor(Context context, String key, int value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putInt(key, value).commit();
        return editor;
    }

    public static SharedPreferences.Editor putBooleanValueInEditor(Context context, String key, boolean value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value).commit();
        return editor;
    }



    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Config.MY_PREF, 0);
    }

    public static boolean isLogin(Context context){
        return getSharedPreferences(context).getBoolean("isLogin",false);
    }


    public static String getApiToken(Context context){
        return getSharedPreferences(context).getString("api_token","");
    }


}
