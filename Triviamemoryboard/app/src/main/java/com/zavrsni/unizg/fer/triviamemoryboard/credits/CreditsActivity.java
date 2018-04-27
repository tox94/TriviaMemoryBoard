package com.zavrsni.unizg.fer.triviamemoryboard.credits;

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
import android.widget.ImageView;

import com.zavrsni.unizg.fer.triviamemoryboard.MainActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.R;
import com.zavrsni.unizg.fer.triviamemoryboard.leaderboard.LeaderboardActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.LevelSelectActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fifth.FifthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.first.FirstLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fourth.FourthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.second.SecondLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.third.ThirdLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.statistics.StatisticsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.tutorial.TutorialActivity;

public class CreditsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*ImageView iv = (ImageView) findViewById(R.id.imageView_drawer_logo);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_leaderboard) {
            Intent intent=new Intent(getApplicationContext(), LeaderboardActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_tutorial) {
            Intent intent=new Intent(getApplicationContext(), TutorialActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_lev_1) {
            Intent intent=new Intent(getApplicationContext(), FirstLevelActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_lev_2) {
            Intent intent=new Intent(getApplicationContext(), SecondLevelActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_lev_3) {
            Intent intent=new Intent(getApplicationContext(), ThirdLevelActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_lev_4) {
            Intent intent=new Intent(getApplicationContext(), FourthLevelActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_lev_5) {
            Intent intent=new Intent(getApplicationContext(), FifthLevelActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_about) {

        }else if (id == R.id.nav_statistics) {
            Intent intent=new Intent(getApplicationContext(), StatisticsActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
