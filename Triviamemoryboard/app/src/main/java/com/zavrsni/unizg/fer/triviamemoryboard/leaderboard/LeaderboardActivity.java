package com.zavrsni.unizg.fer.triviamemoryboard.leaderboard;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zavrsni.unizg.fer.triviamemoryboard.MainActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.R;
import com.zavrsni.unizg.fer.triviamemoryboard.credits.CreditsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.db.DbCreator;
import com.zavrsni.unizg.fer.triviamemoryboard.db.question.Question;
import com.zavrsni.unizg.fer.triviamemoryboard.db.question.QuestionFromDb;
import com.zavrsni.unizg.fer.triviamemoryboard.db.question.SqlQuestionRepository;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.ResultFromDb;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.SqlResultRepository;
import com.zavrsni.unizg.fer.triviamemoryboard.leaderboard.comparators.ResultComparator;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fifth.FifthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.first.FirstLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fourth.FourthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.second.SecondLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.third.ThirdLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.statistics.StatisticsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.tutorial.TutorialActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LeaderboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SqlResultRepository repo;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DbCreator dbc = new DbCreator(getApplicationContext());

        repo = new SqlResultRepository(getApplicationContext());

        table = (TableLayout) findViewById(R.id.table_scoreboard);

        RadioGroup rg = (RadioGroup) findViewById(R.id.RadioGroupLevel);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        for(int i=0; i<5; i++){
            RadioButton rb = (RadioButton) rg.getChildAt(i);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickButton(v);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        RadioButton rb1 = (RadioButton) rg.getChildAt(0);
        rb1.setChecked(true);
        rb1.callOnClick();
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

    public void onClickButton(View v){
        Button btn = (Button) findViewById(R.id.button_goToLevel);
        switch(v.getTag().toString()){
            case "1":
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(), FirstLevelActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                fillTable(1);
                break;
            case "2":
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(), SecondLevelActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                fillTable(2);
                break;
            case "3":
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(), ThirdLevelActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                fillTable(3);
                break;
            case "4":
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(), FourthLevelActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                fillTable(4);
                break;
            case "5":
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(), FifthLevelActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                fillTable(5);
                break;
            default:
                break;
        }
    }

    public void fillTable(int i){
        int cnt = 1;
        Log.d("DEBUG", "Traženi level: " + i);
        TableRow header = (TableRow) table.getChildAt(0);
        table.removeAllViews();
        table.addView(header);
        ArrayList<ResultFromDb> data = repo.getAllResults(i);
        Collections.sort(data, new ResultComparator());
        List<ResultFromDb> results;
        if (data.size()>=10)
            results = data.subList(0, 10);
        else
            results = data;
        for(ResultFromDb res: results){
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            params1.setMargins(6, 6, 6, 6);

            TableRow row = new TableRow(this);
            row.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            for(int j=0; j<4; j++){
                TextView tw = new TextView(this);
                tw.setTextSize(20);
                tw.setGravity(Gravity.CENTER);
                tw.setTextColor(getResources().getColor(R.color.colorPrimary));
                tw.setBackgroundColor(Color.WHITE);
                switch (j){
                    case 0:
                        tw.setText(String.valueOf(cnt));
                        cnt++;
                        break;
                    case 1:
                        tw.setText(res.getUser());
                        break;
                    case 2:
                        tw.setText(String.valueOf(res.getTime()));
                        break;
                    case 3:
                        tw.setText(String.valueOf(res.getResult()));
                        break;
                    default:
                        break;
                }
                row.addView(tw, params1);
            }
            table.addView(row);
        }
    }
}
