package com.techeasesol.myface.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.techeasesol.myface.R;
import com.techeasesol.myface.firebase.MyJobService;
import com.techeasesol.myface.fragments.HomeFragment;
import com.techeasesol.myface.fragments.LoginFragment;
import com.techeasesol.myface.utilities.GeneralUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        if(GeneralUtils.isLogin(this)){
            startActivity(new Intent(MainActivity.this,DrawerActivity.class));
        }
        else {
            GeneralUtils.connectFragmentWithoutBack(this,new LoginFragment());
        }


    }
}
