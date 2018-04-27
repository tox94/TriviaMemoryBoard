package com.zavrsni.unizg.fer.triviamemoryboard.statistics.help;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zavrsni.unizg.fer.triviamemoryboard.MainActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.R;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.ResultFromDb;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.SqlResultRepository;
import com.zavrsni.unizg.fer.triviamemoryboard.statistics.StatisticsActivity;

import java.util.ArrayList;

public class HelpingStatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helping_stat);
        SqlResultRepository rep = new SqlResultRepository(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout_helpStat);
        for(int i=1; i<6; i++){
            TextView t = new TextView(this);
            t.setText("Razina: " + i);
            layout.addView(t);
            ArrayList<ResultFromDb> list = rep.getAllResults(i);
            for (ResultFromDb res: list){
                TextView tv = new TextView(this);
                tv.setText(res.details());
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tv.setTextSize(12);
                layout.addView(tv);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(), StatisticsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return true;
    }
}
