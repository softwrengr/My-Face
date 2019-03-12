package com.techeasesol.myface.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.classes.BottomSheetClass;
import com.techeasesol.myface.fragments.AboutSendFragment;
import com.techeasesol.myface.fragments.HomeFragment;
import com.techeasesol.myface.fragments.LoginFragment;
import com.techeasesol.myface.fragments.NearPeoplesFragment;
import com.techeasesol.myface.fragments.RecievedCardFragment;
import com.techeasesol.myface.fragments.SettingFragment;
import com.techeasesol.myface.fragments.mycards.CardsFragment;
import com.techeasesol.myface.utilities.GeneralUtils;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        this.setTitle("Cards");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GeneralUtils.connectDrawerFragmentWithoutBack(this, new HomeFragment());


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            GeneralUtils.connectDrawerFragmentWithoutBack(this, new HomeFragment());
        } else if (id == R.id.nav_cards) {
            GeneralUtils.connectDrawerFragment(this, new CardsFragment());
        } else if (id == R.id.nav_setting) {
            GeneralUtils.connectDrawerFragment(this, new SettingFragment());

        } else if (id == R.id.nav_rate_us) {

        } else if (id == R.id.nav_logout) {
            GeneralUtils.putBooleanValueInEditor(this, "isLogin", false);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
