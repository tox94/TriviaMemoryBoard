package com.zavrsni.unizg.fer.triviamemoryboard.levels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zavrsni.unizg.fer.triviamemoryboard.MainActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.R;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fifth.FifthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.first.FirstLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fourth.FourthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.second.SecondLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.third.ThirdLevelActivity;

public class LevelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn1 = (Button) findViewById(R.id.button_lvl_1);
        Button btn2 = (Button) findViewById(R.id.button_lvl_2);
        Button btn3 = (Button) findViewById(R.id.button_lvl_3);
        Button btn4 = (Button) findViewById(R.id.button_lvl_4);
        Button btn5 = (Button) findViewById(R.id.button_lvl_5);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), FirstLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), SecondLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), ThirdLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), FourthLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), FifthLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return true;
    }
}
