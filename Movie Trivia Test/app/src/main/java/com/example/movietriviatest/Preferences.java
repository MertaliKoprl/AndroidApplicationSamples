package com.example.movietriviatest;

import java.io.Serializable;

public class Preferences implements Serializable{
    private boolean history;
    private boolean action;
    private boolean sciFi;
    private boolean comedy;
    private boolean horror;
    private int historyNumberQuestions;
    private int actionNumberQuestions;
    private int sciFiNumberQuestions;
    private int comedyNumberQuestions;
    private int horrorNumberQuestions;


    public Preferences(boolean history, boolean action, boolean sciFi,boolean comedy,boolean horror,int historyNumberQuestions,int actionNumberQuestions,int sciFiNumberQuestions,int comedyNumberQuestions,int horrorNumberQuestions){
        this.history = history;
        this.action = action;
        this.sciFi = sciFi;
        this.comedy=comedy;
        this.horror=horror;
        this.historyNumberQuestions=historyNumberQuestions;
        this.actionNumberQuestions=actionNumberQuestions;
        this.sciFiNumberQuestions=sciFiNumberQuestions;
        this.comedyNumberQuestions=comedyNumberQuestions;
        this.horrorNumberQuestions=horrorNumberQuestions;



    }
    public boolean getHistory(){
        return history;

    }
    public boolean getAction(){
        return action;

    }
    public boolean getSciFi(){
        return sciFi;

    } public boolean getComedy(){
        return comedy;

    } public boolean getHorror(){
        return horror;

    }





    public int getActionNumberQuestions() {
        return actionNumberQuestions;
    }

    public int getComedyNumberQuestions() {
        return comedyNumberQuestions;
    }

    public int getHistoryNumberQuestions() {
        return historyNumberQuestions;
    }

    public int getHorrorNumberQuestions() {
        return horrorNumberQuestions;
    }

    public int getSciFiNumberQuestions() {
        return sciFiNumberQuestions;
    }

}
