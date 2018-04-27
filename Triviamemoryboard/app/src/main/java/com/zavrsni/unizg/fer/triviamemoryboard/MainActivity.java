package com.zavrsni.unizg.fer.triviamemoryboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zavrsni.unizg.fer.triviamemoryboard.credits.CreditsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.db.DbCreator;
import com.zavrsni.unizg.fer.triviamemoryboard.leaderboard.LeaderboardActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.LevelSelectActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fifth.FifthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.statistics.StatisticsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.tutorial.TutorialActivity;


public class MainActivity extends AppCompatActivity {

    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = new DbCreator(getApplicationContext()).getWritableDatabase();

        Button btnTutorial = (Button) findViewById(R.id.button_tutorial);
        Button btnLevel = (Button) findViewById(R.id.button_level);
        Button btnLeaderboard = (Button) findViewById(R.id.button_leaderboard);
        Button btnCredits = (Button) findViewById(R.id.button_credits);
        Button btnStatistics = (Button) findViewById(R.id.button_statistics);

        btnTutorial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), TutorialActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLevel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), LevelSelectActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCredits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), CreditsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLeaderboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), LeaderboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnStatistics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), StatisticsActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void onBackPressed() {
        if (exit) {
            finish();// finish activity
        } else {
            Toast.makeText(this, "Pritisnite Natrag još jednom za izlazak iz aplikacije.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
}
