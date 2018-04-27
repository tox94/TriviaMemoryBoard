package com.zavrsni.unizg.fer.triviamemoryboard.levels.third;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.zavrsni.unizg.fer.triviamemoryboard.R;
import com.zavrsni.unizg.fer.triviamemoryboard.credits.CreditsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.db.question.QuestionFromDb;
import com.zavrsni.unizg.fer.triviamemoryboard.db.question.SqlQuestionRepository;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.ResultFromDb;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.SqlResultRepository;
import com.zavrsni.unizg.fer.triviamemoryboard.leaderboard.LeaderboardActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.LevelSelectActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fifth.FifthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.first.FirstLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.fourth.FourthLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.levels.second.SecondLevelActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.statistics.StatisticsActivity;
import com.zavrsni.unizg.fer.triviamemoryboard.tutorial.TutorialActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ThirdLevelActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View fragQuestion;
    View fragMove;
    View fragNick;
    boolean answer = false;
    boolean firstPress = false;
    boolean secondPress = false;
    Chronometer chr;
    ScrollView board;
    int reqMoves;
    int filledMoves = 0;
    LinearLayout lastMoves;
    TableLayout table;
    TextView moves;
    ImageView lastView;
    TableRow.LayoutParams params;
    TextView score;
    Boolean[] clickable;
    Boolean[] locked;
    Boolean[] fieldClicked;
    ArrayList chronoMoves;
    ImageView star1;
    ImageView star2;
    ImageView star3;
    boolean starPoints = false;
    Context ctx;
    int corMoves;
    int incMoves;
    int corQ;
    int incQ;
    int fieldSize = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx = this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        board = (ScrollView) findViewById(R.id.scrollView_board_level_3);

        table = (TableLayout) findViewById(R.id.table_board_level_3);

        score = (TextView) findViewById(R.id.textView_score_points);

        fragQuestion = (View) findViewById(R.id.question_fragment_level_3);
        fragQuestion.setVisibility(View.INVISIBLE);
        fragQuestion.setEnabled(false);

        fragMove = (View) findViewById(R.id.last_moves_fragment_level_3);
        fragMove.setVisibility(View.INVISIBLE);
        fragMove.setEnabled(false);

        fragNick = findViewById(R.id.nick_fragment_level_3);
        fragNick.setVisibility(View.INVISIBLE);
        fragNick.setEnabled(false);

        star1 = (ImageView) findViewById(R.id.imageView_star_1);
        star2 = (ImageView) findViewById(R.id.imageView_star_2);
        star3 = (ImageView) findViewById(R.id.imageView_star_3);

        lastMoves = (LinearLayout) findViewById(R.id.layout_moves);
        chronoMoves = new ArrayList<String>();

        corMoves = 0;
        incMoves = 0;
        corQ = 0;
        incQ = 0;

        reqMoves = 0;

        fieldClicked = new Boolean[fieldSize*fieldSize];
        fieldClicked[0] = true;
        for(int field=1; field<fieldSize*fieldSize; field++){
            fieldClicked[field] = false;
        }

        initTable();

        setClickableAndLocked();

        Toast greeting = Toast.makeText(this, "Krenite!", Toast.LENGTH_LONG);
        greeting.setGravity(Gravity.CENTER, 0, 0);
        greeting.show();
    }

    public void initTable(){
        ScrollView sv = (ScrollView) findViewById(R.id.scrollView_board_level_3);
        int svw = sv.getLayoutParams().width;
        int svh = sv.getLayoutParams().height;

        for(int i = 0; i<fieldSize; i++){
            TableRow row = new TableRow(this);
            row.setBackgroundColor(Color.WHITE);
            params = new TableRow.LayoutParams(
                    svw/8-svw/100,
                    svh/8-svh/100);
            params.setMargins(svw/200, svw/200, svw/200, svw/200);
            for(int j=0; j<fieldSize; j++){
                if(i==7 && j==0){;
                    ImageView iv = new ImageView(this);
                    iv.setLayoutParams(params);
                    iv.setImageResource(R.mipmap.ic_checkerboard);
                    iv.setTag("checkerboard");
                    iv.setId(i*fieldSize + j);
                    row.addView(iv);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initLastMoveFragment(v);
                        }
                    });
                }else{
                    ImageView tv = new ImageView(this);
                    tv.setBackgroundResource(R.drawable.field_button_style_red);
                    tv.setLayoutParams(params);
                    tv.setId(i * fieldSize + j);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(lastView.getId() != v.getId()){
                                if(firstPress == false){
                                    firstPress = true;
                                    lastView.setImageResource(0);
                                    lastView = (ImageView) v;
                                    lastView.setImageResource(R.mipmap.ic_my_location_black_36dp);
                                    int x = v.getId();
                                    int[] fields;
                                    if(x%fieldSize == 0)
                                        fields = new int[]{x-fieldSize, x+1, x+fieldSize};
                                    else if ((x+1)%fieldSize == 0)
                                        fields = new int[]{x-fieldSize, x-1, x+fieldSize};
                                    else
                                        fields = new int[] {x-fieldSize, x-1, x+1, x+fieldSize};
                                    changeClickable(fields);
                                }else{
                                    if(secondPress == false) {
                                        chr = (Chronometer) findViewById(R.id.chronometer);
                                        chr.setBase(SystemClock.elapsedRealtime());
                                        chr.start();
                                        secondPress = true;
                                        int n = Integer.parseInt(score.getText().toString());
                                        n += 10;
                                        score.setText("" + n);
                                    }

                                    initLastMoveFragment(v);


                                }
                            }
                        }
                    });
                    row.addView(tv);
                }
            }
            table.addView(row);
        }
        TableRow tr1 = (TableRow) table.getChildAt(0);
        lastView = (ImageView) tr1.getChildAt(4);
    }

    public void executeMove(View v){

        if(v.getId() == lastView.getId()-fieldSize)
            chronoMoves.add("up");
        else if (v.getId() == lastView.getId()+fieldSize)
            chronoMoves.add("down");
        else if (v.getId() == lastView.getId()-1)
            chronoMoves.add("left");
        else
            chronoMoves.add("right");
        int x = v.getId();
        int[] fields;
        if(x%fieldSize == 0)
            fields = new int[]{x-fieldSize, x+1, x+fieldSize};
        else if ((x+1)%fieldSize == 0)
            fields = new int[]{x-fieldSize, x-1, x+fieldSize};
        else
            fields = new int[] {x-fieldSize, x-1, x+1, x+fieldSize};
        changeClickable(fields);

        if(reqMoves < 3)
            reqMoves++;
        try {
            if (v.getTag() == "checkerboard") {
                lastView.setImageResource(0);
                Toast greeting = Toast.makeText(getApplicationContext(), "Čestitam!\nUspješno ste riješili treću razinu!", Toast.LENGTH_LONG);
                greeting.setGravity(Gravity.CENTER, 0, 0);
                greeting.show();
                chr.stop();
                endLevel();
                return;
            }
        }catch (Exception e){
            Log.d("DEBUG", "No tag");
        }

        lastView.setImageResource(0);
        lastView = (ImageView) v;
        lastView.setImageResource(R.mipmap.ic_my_location_black_36dp);

    }

    public void initLastMoveFragment(final View view){

        changeClickable(null);
        if(reqMoves == 0){
            executeMove(view);
            return;
        }
        changeMovesFragment();
        setMovesListeners();

        ImageButton ibup = (ImageButton) findViewById(R.id.imageButton_up);
        ImageButton ibdown = (ImageButton) findViewById(R.id.imageButton_down);
        ImageButton ibleft = (ImageButton) findViewById(R.id.imageButton_left);
        ImageButton ibright = (ImageButton) findViewById(R.id.imageButton_right);

        ibup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filledMoves<reqMoves){
                    ImageView iv = getImageView();
                    iv.setImageResource(R.mipmap.ic_arrow_upward_black_36dp);
                    iv.setTag("up");
                    filledMoves++;
                }
            }
        });

        ibdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filledMoves<reqMoves) {
                    ImageView iv = getImageView();
                    iv.setImageResource(R.mipmap.ic_arrow_downward_black_36dp);
                    iv.setTag("down");
                    filledMoves++;
                }
            }
        });

        ibleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filledMoves<reqMoves) {
                    ImageView iv = getImageView();
                    iv.setImageResource(R.mipmap.ic_arrow_back_black_36dp);
                    iv.setTag("left");
                    filledMoves++;
                }
            }
        });

        ibright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filledMoves<reqMoves) {
                    ImageView iv = getImageView();
                    iv.setImageResource(R.mipmap.ic_arrow_forward_black_36dp);
                    iv.setTag("right");
                    filledMoves++;
                }
            }
        });

        ImageButton btnCon = (ImageButton) findViewById(R.id.imageButton_confirm);
        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filledMoves<reqMoves){
                    Toast.makeText(getApplicationContext(), "Niste ispunili sva polja!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                final boolean[] right = new boolean[reqMoves];
                for(int n=0; n<reqMoves; n++){
                    right[n] = true;
                }
                boolean cor = true;
                for (int i=0; i<reqMoves; i++){
                    int n = chronoMoves.size();
                    Log.d("DEBUG", "" + i + chronoMoves.get(n-reqMoves+i));
                    Log.d("DEBUG2", "" + lastMoves.getChildAt(i).getTag());
                    switch (chronoMoves.get(n-reqMoves+i).toString()){
                        case "up":
                            if (!lastMoves.getChildAt(i).getTag().equals("up")) {
                                cor = false;
                                right[i] = false;
                            }
                            break;
                        case "down":
                            if (!lastMoves.getChildAt(i).getTag().equals("down")) {
                                cor = false;
                                right[i] = false;
                            }
                            break;
                        case "left":
                            if (!lastMoves.getChildAt(i).getTag().equals("left")) {
                                cor = false;
                                right[i] = false;
                            }
                            break;
                        case "right":
                            if (!lastMoves.getChildAt(i).getTag().equals("right")) {
                                cor = false;
                                right[i] = false;
                            }
                            break;
                        default:
                            cor = false;
                            break;
                    }

                }
                if(cor == true){
                    filledMoves = 0;
                    if (reqMoves==3)
                        corMoves++;
                    fragMove.setVisibility(View.INVISIBLE);
                    fragMove.setEnabled(false);
                    addPoints(view);
                    executeMove(view);
                }else{
                    for (int i=0; i<reqMoves; i++){
                        if (right[i] == false){
                            lastMoves.getChildAt(i).setBackgroundColor(Color.RED);
                        }else
                            lastMoves.getChildAt(i).setBackgroundColor(Color.GREEN);
                    }
                    if (reqMoves==3)
                        incMoves++;
                    int n = Integer.parseInt(score.getText().toString());
                    n -= 2;
                    score.setText("" + n);
                    Toast.makeText(getApplicationContext(), "Krivo!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        fragMove.setVisibility(View.VISIBLE);
        fragMove.setEnabled(true);

    }

    public ImageView getImageView(){
        for (int i=0; i<reqMoves; i++){
            ImageView iv = (ImageView) lastMoves.getChildAt(i);
            if(iv.getTag().toString().equals("empty"))
                return iv;
        }
        return null;
    }

    public void setMovesListeners(){
        for (int i=0; i<reqMoves; i++){
            ImageView iv = (ImageView) lastMoves.getChildAt(i);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView iv = (ImageView) v;
                    if (v.getTag().toString() != "empty"){
                        iv.setBackgroundColor(Color.TRANSPARENT);
                        iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        iv.setImageResource(R.mipmap.ic_help_outline_black_36dp);
                        iv.setTag("empty");
                        filledMoves--;
                    }
                }
            });
        }
    }

    public void addPoints(View v){
        if(fieldClicked[v.getId()] == false) {
            int n = Integer.parseInt(score.getText().toString());
            n += 10;
            score.setText("" + n);
            fieldClicked[v.getId()] = true;
        }else{
            int n = Integer.parseInt(score.getText().toString());
            n -= 5;
            score.setText("" + n);
        }
    }

    public void changeMovesFragment(){

        moves = (TextView) findViewById(R.id.textView_moves_header);
        if (reqMoves == 1)
            moves.setText("Smjer prethodnog poteza:");
        else
            moves.setText("Smjer prethodna " + reqMoves + " poteza:");

        lastMoves = (LinearLayout) findViewById(R.id.layout_moves);
        lastMoves.removeAllViews();
        for (int i=0; i<reqMoves; i++){
            ImageView ib = new ImageView(this);
            ib.setBackgroundColor(Color.TRANSPARENT);
            ib.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            ib.setImageResource(R.mipmap.ic_help_outline_black_36dp);
            ib.setTag("empty");
            lastMoves.addView(ib);
        }
    }

    public void questionMode(final int viewId){
        TextView tv = (TextView) findViewById(R.id.textView_question);
        SqlQuestionRepository rep = new SqlQuestionRepository(getApplicationContext());

        Random r = new Random();
        int qr = r.nextInt(30) + 1;
        QuestionFromDb question = rep.getQuestion(qr);
        tv.setText(question.getQuestion());
        final String correct = question.getCorrect();

        int[] intList = new int[4];
        int intcnt = 0;

        List<Boolean> boolList = new ArrayList<Boolean>(Arrays.asList(new Boolean[4]));
        Collections.fill(boolList, Boolean.FALSE);

        while(boolList.contains(false)){
            int ir = r.nextInt(4) + 1;
            if (boolList.get(ir-1) == false) {
                boolList.set(ir-1, true);
                intList[intcnt] = ir;
                intcnt++;
            }
        }

        final TextView tva = (TextView) findViewById(R.id.textView_ans_a);
        final TextView tvb = (TextView) findViewById(R.id.textView_ans_b);
        final TextView tvc = (TextView) findViewById(R.id.textView_ans_c);
        final TextView tvd = (TextView) findViewById(R.id.textView_ans_d);

        tva.setText("A: " + question.getAns(intList[0]));
        tvb.setText("B: " + question.getAns(intList[1]));
        tvc.setText("C: " + question.getAns(intList[2]));
        tvd.setText("D: " + question.getAns(intList[3]));

        Button btna = (Button) findViewById(R.id.button_a);
        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(String.valueOf(tva.getText()), correct, viewId);
            }
        });
        Button btnb = (Button) findViewById(R.id.button_b);
        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(String.valueOf(tvb.getText()), correct, viewId);
            }
        });
        Button btnc = (Button) findViewById(R.id.button_c);
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(String.valueOf(tvc.getText()), correct, viewId);
            }
        });
        Button btnd = (Button) findViewById(R.id.button_d);
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(String.valueOf(tvd.getText()), correct, viewId);
            }
        });

    }

    public void checkAnswer(String ansx, String ansCorrect, int viewID){
        if(ansx.substring(3).equals(ansCorrect)){
            addStar();
            Toast.makeText(getApplicationContext(), "Točno!",
                    Toast.LENGTH_SHORT).show();
            answer = true;
            corQ += 1;
        }else{
            Toast.makeText(getApplicationContext(), "Netočno!",
                    Toast.LENGTH_SHORT).show();
            incQ += 1;
        }
        changeLocked(viewID);
    }

    public void setClickableAndLocked(){
        clickable = new Boolean[fieldSize*fieldSize];
        locked = new Boolean[fieldSize*fieldSize];

        for(int i=0; i<fieldSize*fieldSize; i++){
            locked[i] = false;
        }

        changeClickable(new int[] {0});

        setLc(new int[]{36, 37, 38}, new int[]{24, 25, 26, 33, 34, 35, 42}, new int[]{50, 51, 58, 59});

        TableRow tr = (TableRow) table.getChildAt(0);
        ImageView iv = (ImageView) tr.getChildAt(0);
        iv.callOnClick();
    }

    public void changeClickable(int[] ints){
        for(int j=0; j<fieldSize*fieldSize; j++){
            clickable[j] = false;
            ImageView iv = (ImageView) findViewById(j);
            iv.setEnabled(false);
        }
        try
        {
            for (int i : ints) {
                try {
                    if ((locked[i] == false || i == 38 || i == 35 || i == 51) && i >= 0 && i < fieldSize*fieldSize) {
                        ImageView iv = (ImageView) findViewById(i);
                        clickable[i] = true;
                        iv.setEnabled(true);
                    }
                } catch (Exception e) {
                    Log.d("DEBUG", "Invalid id: " + i);
                }
            }
        }catch(Exception e){
            Log.d("DEBUG", "Empty int array");
        }
    }

    public void setLc (int[] ints1, int[] ints2, int[] ints3){
        for (int i: ints1){
            locked[i] = true;
            final ImageView iv = (ImageView) findViewById(i);
            iv.setBackgroundResource(R.drawable.button_style);
            if(i == 38){
                iv.setImageResource(R.mipmap.ic_help_outline_black_36dp);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeClickable(null);
                        fragQuestion.setVisibility(View.VISIBLE);
                        fragQuestion.setEnabled(true);
                        questionMode(iv.getId());
                    }
                });
            }
        }
        for (int i: ints2){
            locked[i] = true;
            final ImageView iv = (ImageView) findViewById(i);
            iv.setBackgroundResource(R.drawable.field_button_style_green);
            if(i == 35){
                iv.setImageResource(R.mipmap.ic_help_outline_black_36dp);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeClickable(null);
                        fragQuestion.setVisibility(View.VISIBLE);
                        fragQuestion.setEnabled(true);
                        questionMode(iv.getId());
                    }
                });
            }
        }
        for (int i: ints3){
            locked[i] = true;
            final ImageView iv = (ImageView) findViewById(i);
            iv.setBackgroundResource(R.drawable.field_button_style_yellow);
            if(i == 51){
                iv.setImageResource(R.mipmap.ic_help_outline_black_36dp);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeClickable(null);
                        fragQuestion.setVisibility(View.VISIBLE);
                        fragQuestion.setEnabled(true);
                        questionMode(iv.getId());
                    }
                });
            }
        }
        setPermLocked(new int[]{2, 8, 12, 14, 17, 18, 19, 27, 28, 29, 43, 45, 48, 53, 55});
    }

    public void setPermLocked(int[] ints){
        for(int i: ints){
            ImageView iv = (ImageView) findViewById(i);
            iv.setBackgroundResource(R.drawable.field_button_style_black);
            iv.setImageResource(R.mipmap.ic_remove_circle_white_36dp);
            locked[i] = true;
        }
    }

    public void unlock(int[] ints){
        for(int i: ints){
            ImageView iv = (ImageView) findViewById(i);
            iv.setBackgroundResource(R.drawable.field_button_style_red);
            locked[i] = false;
        }
    }

    public void changeLocked(int i){
        if(answer == true){
            if(i == 38){
                TableRow tr4 = (TableRow) table.getChildAt(4);
                ImageView iv = (ImageView) tr4.getChildAt(6);
                unlock(new int[]{36, 37, 38});
                if(fieldClicked[i] == false) {
                    int n = Integer.parseInt(score.getText().toString());
                    n += 50;
                    score.setText("" + n);
                    fieldClicked[i] = true;
                }else{
                    int n = Integer.parseInt(score.getText().toString());
                    n -= 5;
                    score.setText("" + n);
                }
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initLastMoveFragment(v);
                    }
                });
                executeMove(iv);
            }else if(i == 35){
                TableRow tr4 = (TableRow) table.getChildAt(4);
                ImageView iv = (ImageView) tr4.getChildAt(3);
                unlock(new int[]{24, 25, 26, 34, 35});
                setPermLocked(new int[]{33, 42});
                if(fieldClicked[i] == false) {
                    int n = Integer.parseInt(score.getText().toString());
                    n += 50;
                    score.setText("" + n);
                    fieldClicked[i] = true;
                }else{
                    int n = Integer.parseInt(score.getText().toString());
                    n -= 5;
                    score.setText("" + n);
                }
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initLastMoveFragment(v);
                    }
                });
                executeMove(iv);
            }else if(i == 51){
                TableRow tr6 = (TableRow) table.getChildAt(6);
                ImageView iv = (ImageView) tr6.getChildAt(3);
                unlock(new int[]{51, 58, 59});
                setPermLocked(new int[]{50});
                if(fieldClicked[i] == false) {
                    int n = Integer.parseInt(score.getText().toString());
                    n += 50;
                    score.setText("" + n);
                    fieldClicked[i] = true;
                }else{
                    int n = Integer.parseInt(score.getText().toString());
                    n -= 5;
                    score.setText("" + n);
                }
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initLastMoveFragment(v);
                    }
                });
                executeMove(iv);
            }
        }else{
            int n = Integer.parseInt(score.getText().toString());
            n -= 10;
            score.setText("" + n);
            int[] fields;
            int x = lastView.getId();
            if(x%fieldSize == 0)
                fields = new int[]{x-fieldSize, x+1, x+fieldSize};
            else if ((i+1)%fieldSize == 0)
                fields = new int[]{x-fieldSize, x-1, x+fieldSize};
            else
                fields = new int[] {x-fieldSize, x-1, x+1, x+fieldSize};
            changeClickable(fields);
        }
        fragQuestion.setVisibility(View.INVISIBLE);
        fragQuestion.setEnabled(false);
        answer = false;
    }

    public void addStar(){
        if(starPoints == false){
            if(star1.getTag().toString().equals("false")){
                star1.setTag("true");
                star1.setImageResource(R.mipmap.ic_star_black_18dp);
                return;
            }
            if(star2.getTag().toString().equals("false")){
                star2.setTag("true");
                star2.setImageResource(R.mipmap.ic_star_black_18dp);
                return;
            }
            if(star3.getTag().toString().equals("false")){
                star3.setTag("true");
                star3.setImageResource(R.mipmap.ic_star_black_18dp);
                int n = Integer.parseInt(score.getText().toString());
                n += 50;
                score.setText("" + n);
                Toast.makeText(getApplicationContext(), "Sva polja otključana!",
                        Toast.LENGTH_SHORT).show();
                starPoints = true;
                return;
            }
        }
    }

    public void endLevel(){
        changeClickable(null);

        fragNick.setVisibility(View.VISIBLE);
        fragNick.setEnabled(true);

        final EditText et = (EditText) findViewById(R.id.editText_nick);
        String crono = chr.getText().toString();
        String[] array = crono.split(":");
        final int seconds = Integer.parseInt(array[0])*60 + Integer.parseInt(array[1]);
        TextView tv = (TextView) findViewById(R.id.textView_score_points);
        int min = Integer.parseInt(array[0])*20;
        final int points = Integer.parseInt(tv.getText().toString()) - min;
        Log.d("TIME", "" + seconds);
        Log.d("POINTS", "" + points);

        Button btnEnd = (Button) findViewById(R.id.button_confirmNickname);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlResultRepository rep = new SqlResultRepository(getApplicationContext());
                ResultFromDb res = null;
                Random r = new Random();
                int qr = 0;
                do{
                    qr = r.nextInt(5000) + 1;
                    try{
                        res = rep.getResult(qr);
                    }catch(Exception e){
                        Log.d("DEBUG", "Već postoji id = " + qr);
                    }
                }while(res != null);
                int cnt  = qr;
                final String nick = et.getText().toString();
                int ord = 0;
                try{
                    ArrayList<ResultFromDb> allResults = rep.getAllResultsForUser(nick, 3);
                    ord = allResults.size() + 1;
                }catch(Exception e){
                    Log.d("DEBUG", "Ne postoji takav zapis");
                }
                if(ord == 0)
                    ord = 1;
                ResultFromDb result = new ResultFromDb(cnt, nick, 3, seconds, points, corMoves, incMoves, ord, corQ, incQ);
                rep.createResult(result);
                Intent intent=new Intent(getApplicationContext(), LevelSelectActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Napredak se ne sprema")
                .setMessage("Želite li izaći iz razine?")
                .setPositiveButton("Da", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(), LevelSelectActivity.class);
                        startActivity(intent);
                        finish();
                    }

                })
                .setNegativeButton("Ne", null)
                .show();
    }

    @Override
    public void onConfigurationChanged(Configuration newconfig){
        super.onConfigurationChanged(newconfig);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_leaderboard) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), LeaderboardActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();
        } else if (id == R.id.nav_tutorial) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), TutorialActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();
        } else if (id == R.id.nav_lev_1) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), FirstLevelActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();
        } else if (id == R.id.nav_lev_2) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), SecondLevelActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();
        } else if (id == R.id.nav_lev_3) {
            /*new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), ThirdLevelActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();*/
        } else if (id == R.id.nav_lev_4) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), FourthLevelActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();
        } else if (id == R.id.nav_lev_5) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), FifthLevelActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();
        } else if (id == R.id.nav_about) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), CreditsActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();
        }else if (id == R.id.nav_statistics) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Napredak se ne sprema")
                    .setMessage("Želite li izaći iz razine?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), StatisticsActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("Ne", null)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
