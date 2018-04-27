package com.zavrsni.unizg.fer.triviamemoryboard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.ViewDebug;

import java.util.ArrayList;
import java.util.List;

public class DbInit {
    private final Context ctx;

    public DbInit(Context ctx){
        this.ctx = ctx;
    }

    public void initAll(SQLiteDatabase db){
        Log.d("DEBUG", "initAll initiated");
        try{
            List<String> data = getData();
            for (String s: data){
                db.execSQL(s);
            }
        }catch (Exception e){
            Log.d("DEBUG", "initAll exception: " + e.getLocalizedMessage());
        }
    }

    private List<String> getData(){
        List<String> data = new ArrayList<>();

        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (1, 'Glavni grad Australije je:', 'Sydney', 'Melbourne', 'Perth', 'Canberra', 'Canberra')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (2, 'Mjerna jedinica napona je:', 'Volt', 'Amper', 'Watt', 'Ohm', 'Volt')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (3, 'Najbolji strijelac u povijesti nogometne Lige prvaka je:', 'Cristiano Ronaldo', 'Raul', 'Lionel Messi', 'Ruud van Nistelrooy', 'Cristiano Ronaldo')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (4, 'Koje godine je okrunjen prvi hrvatski kralj Tomislav?', '925.', '935.', '915.', '945.', '925.')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (5, 'Gdje se nalazi more tišine?', 'Na Mjesecu', 'Na Marsu', 'Na Jupiteru', 'Na Zemlji', 'Na Mjesecu')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (6, 'Penicilin je otkrio:', 'Louis Pasteur', 'Alexander Fleming', 'Carl von Linne', 'Charles Darwin', 'Louis Pasteur')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (7, 'Iz kojeg grada su Beatlesi?', 'London', 'Birmingham', 'Manchester', 'Liverpool', 'Liverpool')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (8, 'Tko je redatelj trilogije Gospodar Prstenova?', 'Martin Scorsese', 'Peter Jackson', 'Quentin Tarantino', 'Steven Spielberg', 'Peter Jackson')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (9, 'Šahovska figura koja se može kretati samo dijagonalno je:', 'Kula', 'Lovac', 'Kraljica', 'Kralj', 'Lovac')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (10, 'Uz plavu i crvenu, primarna boja je i:', 'Žuta', 'Zelena', 'Narančasta', 'Bijela', 'Žuta')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (11, 'Kristofor Kolumbo je doplovio do Amerike: ', '1492.', '1494.', '1592.', '1594.', '1492.')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (12, 'Biti ili ne biti je poznata rečenica iz djela:', 'Don Quijote', 'Hamlet', 'Veliki Gatsby', 'San ivanjske noći', 'Hamlet')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (13, 'Norma Jean Baker je poznatija pod imenom:', 'Marilyn Monroe', 'Lady Gaga', 'Beyonce', 'Audrey Hepburn', 'Marilyn Monroe')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (14, 'Što na grčkom znači riječ biblion?', 'Povijest', 'Vjera', 'Bog', 'Knjiga', 'Knjiga')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (15, 'Koje životinje su najpoznatiji tragači tartufa?', 'Psi', 'Konji', 'Krave', 'Svinje', 'Svinje')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (16, 'Koliko žica ima violončelo?', '4', '5', '6', '8', '4')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (17, 'Barut je izumljen u:', 'Mezopotamiji', 'Kini', 'Indiji', 'Japanu', 'Kini')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (18, 'Baza heksadekadskog sustava je broj:', '8', '6', '16', '15', '16')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (19, 'Koliko rupa ima stol za biljar?', '2', '4', '6', '8', '6')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (20, 'Ledište vode u Kelvinima iznosi:', '273,15', '283,15', '293,15', '373,15', '273,15')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (21, 'Najmanja kost u tijelu se nalazi u:', 'šaci', 'nozi', 'uhu', 'laktu', 'uhu')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (22, 'Koje godine je završio II. Svjetski rat?', '1944.', '1945.', '1947.', '1949.', '1945.')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (23, 'Koja rijeka prolazi kroz Rim?', 'Tiber', 'Po', 'Arno', 'Dunav', 'Tiber')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (24, 'Aktualni svjetski prvak u nogometu je:', 'Brazil', 'Španjolska', 'Argentina', 'Njemačka', 'Njemačka')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (25, 'Koji je ocean najveći?', 'Atlantski', 'Tihi', 'Indijski', 'Arktički', 'Tihi')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (26, 'Što označava slovo C u kratici LCD?', 'Color', 'Crystal', 'Cover', 'Cyan', 'Crystal')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (27, 'Koliko ima polja na šahovksoj ploči?', '64', '48', '32', '128', '64')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (28, 'Koja je poznata pjevačica glumila u Pobješnjelom Maxu?', 'Tina Turner', 'Janet Jackson', 'Madonna', 'Whitney Houston', 'Tina Turner')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (29, 'Koja je najveća živuća životinja?', 'Slon', 'Nosorog', 'Plavetni kit', 'Žirafa', 'Plavetni kit')");
        data.add("INSERT INTO Question (_ID, Text, Ans1, Ans2, Ans3, Ans4, Correct) VALUES (30, 'Koja je bila prva životinjska vrsta u svemiru?', 'Pas', 'Mačka', 'Majmun', 'Miš', 'Pas')");

        /*data.add("INSERT INTO Result(_ID, user, level, time, result) VALUES (1, 'tox', 1, 20, 40)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (2, 'tox', 1, 20, 50)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (3, 'tox', 1, 20, 80)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (4, 'tox', 1, 18, 30)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (5, 'tox', 1, 20, 20)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (6, 'tox', 1, 20, 23)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (7, 'tox', 1, 50, 34)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (8, 'tox', 1, 20, 52)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (9, 'tox', 1, 20, 63)");
        data.add("INSERT INTO Result(_ID, User, Level, Time, Result) VALUES (10, 'tox', 1, 20, 71)");*/

        return data;
    }
}
