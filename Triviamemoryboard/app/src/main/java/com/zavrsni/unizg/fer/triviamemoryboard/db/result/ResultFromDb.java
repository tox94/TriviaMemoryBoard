package com.zavrsni.unizg.fer.triviamemoryboard.db.result;

import com.zavrsni.unizg.fer.triviamemoryboard.Comparator.IComparable;
import com.zavrsni.unizg.fer.triviamemoryboard.Comparator.IDetails;

import java.io.Serializable;

public class ResultFromDb implements Serializable, IComparable<ResultFromDb>, IDetails{
    private int id;
    private String user;
    private int level;
    private int time;
    private int result;
    private int corMoves;
    private int incMoves;
    private int ordNum;
    private int corQues;
    private int incQues;

    public ResultFromDb(int id, String user, int level, int time, int result, int corMoves, int incMoves, int ordNum, int corQues, int incQues){
        this.id = id;
        this.user = user;
        this.level = level;
        this.time = time;
        this.result = result;
        this.corMoves = corMoves;
        this.incMoves = incMoves;
        this.ordNum= ordNum;
        this.corQues = corQues;
        this.incQues = incQues;
    }

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getUser(){ return user; }
    public void setUser(String user){ this.user = user; }

    public int getLevel(){ return level; }
    public void setLevel(int level){ this.level = level; }

    public int getTime(){ return time; }
    public void setTime(int time){ this.time = time; }

    public int getResult(){ return result; }
    public void setResult(int result){ this.result = result; }

    public int getCorMoves(){ return corMoves; }
    public void setCorMoves(int corMoves){ this.corMoves = corMoves; }

    public int getIncMoves(){ return incMoves; }
    public void setIncMoves(int incMoves){ this.incMoves = incMoves; }

    public int getOrdNun(){ return ordNum; }
    public void setOrdNum(int ordNum){ this.ordNum = ordNum; }

    public int getCorQues(){ return corQues; }
    public void setCorQues(int corQues){ this.corQues = corQues; }

    public int getIncQues(){ return incQues; }
    public void setIncQues(int incQues){ this.incQues = incQues; }

    @Override
    public boolean equals(Object o){
        if (o instanceof ResultFromDb){
            ResultFromDb other = (ResultFromDb) o;
            return result == other.result;
        }
        return false;
    }

    @Override
    public String details(){
        StringBuilder sb = new StringBuilder();
        sb.append("N: ").append(user).append(" ")
                .append("# ").append(ordNum).append(" ")
                .append("B: ").append(result).append(" ")
                .append("T: ").append(time).append("s ")
                .append("K(T/N): ").append(corMoves).append("/").append(incMoves).append(" ")
                .append("O(T/N): ").append(corQues).append("/").append(incQues);
        return sb.toString();
    }
}
