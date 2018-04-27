package com.zavrsni.unizg.fer.triviamemoryboard.db.question;

import com.zavrsni.unizg.fer.triviamemoryboard.Comparator.IComparable;
import com.zavrsni.unizg.fer.triviamemoryboard.Comparator.IDetails;

import java.io.Serializable;

public class QuestionFromDb implements Serializable, IComparable<QuestionFromDb>, IDetails {
    private int id;
    private String question;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private String correct;

    public QuestionFromDb(int id, String question, String ansA, String ansB, String ansC, String ansD, String correct){
        this.id = id;
        this.question = question;
        this.ansA = ansA;
        this.ansB = ansB;
        this.ansC = ansC;
        this.ansD = ansD;
        this.correct = correct;
    }

    public String getAns(int i){
        switch(i){
            case 1:
                return ansA;
            case 2:
                return ansB;
            case 3:
                return ansC;
            case 4:
                return ansD;
            default:
                throw new RuntimeException(String.format("Invalid question counter: %s. Has to be 1-4.",
                        String.valueOf(i)));
        }
    }

    public int getId(){ return id; }

    public String getQuestion(){ return question; }

    public String getAnsA(){ return ansA; }

    public String getAnsB(){ return ansB; }

    public String getAnsC(){ return ansC; }

    public String getAnsD(){ return ansD; }

    public String getCorrect(){ return correct; }

    public void setId(int id){ this.id = id; }

    public void setQuestion(String question){ this.question = question; }

    public void setAnsA(String ansA){ this.ansA = ansA; }

    public void setAnsB(String ansB){ this.ansB = ansB; }

    public void setAnsC(String ansC){ this.ansC = ansC; }

    public void setAnsD(String ansD){ this.ansD = ansD; }

    public void setCorrect(String correct){ this.correct = correct; }

    @Override
    public boolean equals(Object o){
        if (o instanceof QuestionFromDb){
            QuestionFromDb other = (QuestionFromDb) o;
            return question.equals(other.question);
        }
        return false;
    }

    @Override
    public String details(){
        StringBuilder sb = new StringBuilder();
        sb.append(question).append("\n")
            .append("A: ").append(ansA).append("\t\t\t")
            .append("B: ").append(ansB).append("\n")
            .append("C: ").append(ansC).append("\t\t\t")
            .append("D: ").append(ansD).append("\n");
        return sb.toString();
    }

}
