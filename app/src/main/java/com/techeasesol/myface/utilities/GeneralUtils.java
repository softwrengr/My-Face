package com.techeasesol.myface.utilities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.techeasesol.myface.R;

public class GeneralUtils {

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


}
