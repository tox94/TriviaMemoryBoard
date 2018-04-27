package com.zavrsni.unizg.fer.triviamemoryboard.tutorial;

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
import android.widget.RadioButton;
import android.widget.TextView;

import com.zavrsni.unizg.fer.triviamemoryboard.MainActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.R;
import com.zavrsni.unizg.fer.triviamemoryboard.credits.CreditsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.leaderboard.LeaderboardActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fifth.FifthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.first.FirstLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fourth.FourthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.second.SecondLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.third.ThirdLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.statistics.StatisticsActivity;

public class TutorialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View fragGame;
    View fragScoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragGame = (View) findViewById(R.id.game_rules_fragment);
        fragGame.setVisibility(View.INVISIBLE);
        fragGame.setEnabled(false);
        fragScoring = (View) findViewById(R.id.scoring_rules_fragment);
        fragScoring.setVisibility(View.INVISIBLE);
        fragScoring.setEnabled(false);

        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton_gameRules);
        RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton_scoringRules);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragScoring.setVisibility(View.INVISIBLE);
                fragScoring.setEnabled(false);
                fragGame.setVisibility(View.VISIBLE);
                fragGame.setEnabled(true);
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragGame.setVisibility(View.INVISIBLE);
                fragGame.setEnabled(false);
                fragScoring.setVisibility(View.VISIBLE);
                fragScoring.setEnabled(true);
            }
        });

        rb1.setChecked(true);
        rb1.callOnClick();
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
            Intent intent=new Intent(getApplicationContext(), CreditsActivity.class);
            startActivity(intent);
            finish();
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
