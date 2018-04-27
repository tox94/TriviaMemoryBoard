package com.zavrsni.unizg.fer.triviamemoryboard.leaderboard.comparators;

import com.zavrsni.unizg.fer.triviamemoryboard.db.result.ResultFromDb;

import java.util.Comparator;

public class ResultComparator implements Comparator<ResultFromDb> {
    @Override
    public int compare(ResultFromDb res1, ResultFromDb res2){
        if((res1.getResult() > res2.getResult()) ||
                (res1.getResult() == res2.getResult() && res1.getTime() < res2.getTime()) ||
                (res1.getResult() == res2.getResult() && res1.getTime() == res2.getTime() && res1.getUser().compareTo(res2.getUser()) == '1' ))
            return -1;
        else
            return 1;
    }
}
