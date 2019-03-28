package com.techeasesol.myface.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.firebase.MyJobService;
import com.techeasesol.myface.utilities.ShareUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getSupportActionBar().hide();

        startService(new Intent(this, MyJobService.class));

        if (getIntent().hasExtra("id")) { //dy line ta gora
            ShareUtils.getEditor(this).putString("send_card_id", String.valueOf(getIntent().getExtras().getString("id"))).commit();
            ShareUtils.getEditor(this).putString("card_message", String.valueOf(getIntent().getExtras().getString("message"))).commit();


        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        },2000);
    }
}
//firebase open ka