package com.example.movietriviatest;

import java.io.Serializable;

public class Preferences implements Serializable {
    private boolean history;
    private boolean action;
    private boolean sciFi;

    private int historyNumberQuestions;
    private int actionNumberQuestions;
    private int sciFiNumberQuestions;


    public Preferences(boolean history, boolean action, boolean sciFi, int historyNumberQuestions, int actionNumberQuestions, int sciFiNumberQuestions) {
        this.history = history;
        this.action = action;
        this.sciFi = sciFi;

        this.historyNumberQuestions = historyNumberQuestions;
        this.actionNumberQuestions = actionNumberQuestions;
        this.sciFiNumberQuestions = sciFiNumberQuestions;


    }

    public boolean getHistory() {
        return history;

    }

    public boolean getAction() {
        return action;

    }

    public boolean getSciFi() {
        return sciFi;

    }


    public int getActionNumberQuestions() {
        return actionNumberQuestions;
    }


    public int getHistoryNumberQuestions() {
        return historyNumberQuestions;
    }


    public int getSciFiNumberQuestions() {
        return sciFiNumberQuestions;
    }

    public int getTotalNumberOfQuestions() {
        if (getSciFi()) {
           if(getAction()){
               if(getHistory()){
                   return getActionNumberQuestions()+getSciFiNumberQuestions()+getHistoryNumberQuestions();
               }
               return getActionNumberQuestions()+getSciFiNumberQuestions();


           }
           return getSciFiNumberQuestions();
        }
        if (getAction()) {
            if(getHistory()){
                return getActionNumberQuestions()+getHistoryNumberQuestions();

            }
            return getActionNumberQuestions();
        }
        return getHistoryNumberQuestions();
    }

}
