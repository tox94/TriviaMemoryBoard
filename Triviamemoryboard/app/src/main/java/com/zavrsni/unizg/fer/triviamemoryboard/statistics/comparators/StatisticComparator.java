package com.zavrsni.unizg.fer.triviamemoryboard.statistics.comparators;

import com.zavrsni.unizg.fer.triviamemoryboard.db.result.ResultFromDb;

import java.util.Comparator;
import java.util.Map;

public class StatisticComparator<K, V> implements Comparator<Map.Entry<K, V>> {

    @Override
    public int compare(Map.Entry<K, V> entry1, Map.Entry<K, V> entry2){
        String[] s1 = entry1.getValue().toString().split("/");
        String[] s2 = entry2.getValue().toString().split("/");
        String user1 = entry1.getKey().toString();
        String user2 = entry2.getKey().toString();
        float cor1 = Float.valueOf(s1[0]);
        float inc1 = Float.valueOf(s1[1]);
        float cor2 = Float.valueOf(s2[0]);
        float inc2 = Float.valueOf(s2[1]);
        float perc1 = cor1/(cor1+inc1);
        float perc2 = cor2/(cor2+inc2);
        if(perc1 > perc2 || (perc1==perc2 && cor1>cor2) || (perc1 == perc2 && cor1 == cor2 && user1.compareTo(user2)>0))
            return -1;
        else
            return 1;
    }
}
