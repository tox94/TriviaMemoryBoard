package com.zavrsni.unizg.fer.triviamemoryboard.statistics;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zavrsni.unizg.fer.triviamemoryboard.MainActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.R;
import com.zavrsni.unizg.fer.triviamemoryboard.credits.CreditsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.ResultFromDb;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.SqlResultRepository;
import com.zavrsni.unizg.fer.triviamemoryboard.leaderboard.LeaderboardActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.leaderboard.comparators.ResultComparator;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fifth.FifthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.first.FirstLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fourth.FourthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.second.SecondLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.third.ThirdLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.statistics.comparators.StatisticComparator;
import com.zavrsni.unizg.fer.triviamemoryboard.statistics.help.HelpingStatActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.tutorial.TutorialActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatisticsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SqlResultRepository repo;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        repo = new SqlResultRepository(getApplicationContext());

        table = (TableLayout) findViewById(R.id.table_statisticScoreboard);

        /*Button btn = (Button) findViewById(R.id.button_deepStat);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpingStatActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

        RadioGroup rg = (RadioGroup) findViewById(R.id.RadioGroupStatistics);

        for(int i=0; i<5; i++){
            RadioButton rb = (RadioButton) rg.getChildAt(i);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickButton(v);
                }
            });
        }

        RadioButton rb1 = (RadioButton) rg.getChildAt(0);
        rb1.setChecked(true);
        rb1.callOnClick();
    }

    public void onClickButton(View v){
        switch(v.getTag().toString()){
            case "1":
                fillTable(1);
                break;
            case "2":
                fillTable(2);
                break;
            case "3":
                fillTable(3);
                break;
            case "4":
                fillTable(4);
                break;
            case "5":
                fillTable(5);
                break;
            default:
                break;
        }
    }

    public void fillTable(final int i){
        int cnt = 1;
        int totcor = 0;
        int totinc = 0;
        Map<String, String> userMap = new HashMap<String, String>();
        Log.d("DEBUG", "Traženi level: " + i);
        TableRow header = (TableRow) table.getChildAt(0);
        table.removeAllViews();
        table.addView(header);
        Button btn = (Button) findViewById(R.id.button_goToLevel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(i){
                    case 1:
                        Intent intent=new Intent(getApplicationContext(), FirstLevelActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        Intent intent2=new Intent(getApplicationContext(), SecondLevelActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case 3:
                        Intent intent3=new Intent(getApplicationContext(), ThirdLevelActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case 4:
                        Intent intent4=new Intent(getApplicationContext(), FourthLevelActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case 5:
                        Intent intent5=new Intent(getApplicationContext(), FifthLevelActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });

        TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params1.setMargins(6, 6, 6, 6);
        int usercnt = 1;
        ArrayList<String> users = repo.getAllUsers(i);
        ArrayList<String> list = new ArrayList<>();
        for (String user: users){
            if (!list.contains(user))
                list.add(user);
        }
        for (String user: list){
            int cor = 0;
            int inc = 0;
            ArrayList<ResultFromDb> data = repo.getAllResultsForUser(user, i);
            for (ResultFromDb res : data) {
                cor += res.getCorMoves();
                totcor += res.getCorMoves();
                inc += res.getIncMoves();
                totinc += res.getIncMoves();
            }
            String s = String.valueOf(cor) + "/" + String.valueOf(inc);
            userMap.put(user, s);
        }

        List<Map.Entry<String, String>> userList = new LinkedList<>(userMap.entrySet());

        Collections.sort(userList, new StatisticComparator<String, String>());

        Map<String, String> map = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : userList){
            map.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String> user : map.entrySet()) {
            usercnt++;
            TableRow row = new TableRow(this);
            row.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            String[] val = user.getValue().split("/");
            int cor = Integer.valueOf(val[0]);
            int inc = Integer.valueOf(val[1]);


            ArrayList<ResultFromDb> data = repo.getAllResultsForUser(user.getKey(), i);
            List<ResultFromDb> results = data;

            for (int j = 0; j < 4; j++) {
                TextView tw = new TextView(this);
                tw.setTextSize(20);
                tw.setGravity(Gravity.CENTER);
                tw.setTextColor(getResources().getColor(R.color.colorPrimary));
                tw.setBackgroundColor(Color.WHITE);
                switch (j) {
                    case 0:
                        tw.setText(String.valueOf(cnt));
                        cnt++;
                        break;
                    case 1:
                        tw.setText(user.getKey());
                        break;
                    case 2:
                        tw.setText(user.getValue());
                        break;
                    case 3:
                        Log.d("VRIJEDNOST", "Točni: " + cor);
                        Log.d("VRIJEDNOST", "Netočni: " + inc);
                        int total = cor + inc;
                        float perc = ((float)cor/(float)total)*100;
                        Log.d("VRIJEDNOST", "Postotak: " + perc);
                        tw.setText(String.valueOf(new DecimalFormat("##.##").format(perc)) + "%");
                        break;
                    default:
                        break;
                }
                row.addView(tw, params1);
            }
            table.addView(row);
        }
        TableRow row = new TableRow(this);
        row.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        for (int j = 0; j < 4; j++) {
            TextView tw = new TextView(this);
            tw.setTextSize(20);
            tw.setGravity(Gravity.CENTER);
            tw.setTextColor(Color.WHITE);
            tw.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tw.setTypeface(null, Typeface.BOLD);
            switch (j) {
                case 0:
                    tw.setText("");
                    cnt++;
                    break;
                case 1:
                    tw.setText("∑");
                    break;
                case 2:
                    tw.setText(String.valueOf(totcor) + "/" + String.valueOf(totinc));
                    break;
                case 3:
                    Log.d("VRIJEDNOST", "Točni: " + totcor);
                    Log.d("VRIJEDNOST", "Netočni: " + totinc);
                    int total = totcor + totinc;
                    float perc = ((float)totcor/(float)total)*100;
                    Log.d("VRIJEDNOST", "Postotak: " + perc);
                    tw.setText(String.valueOf(new DecimalFormat("##.##").format(perc)) + "%");
                    break;
                default:
                    break;
            }
            row.addView(tw, params1);
        }
        table.addView(row);
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
            Intent intent=new Intent(getApplicationContext(), CreditsActivity.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_statistics) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
