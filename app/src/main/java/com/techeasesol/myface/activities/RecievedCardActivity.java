package com.techeasesol.myface.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.techeasesol.myface.R;
import com.techeasesol.myface.fragments.RecievedCardFragment;
import com.techeasesol.myface.utilities.GeneralUtils;

public class RecievedCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved_card);

        GeneralUtils.connectRecievedCardFragments(this,new RecievedCardFragment());
    }
}
