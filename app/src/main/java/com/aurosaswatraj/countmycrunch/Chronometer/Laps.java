package com.aurosaswatraj.countmycrunch.Chronometer;

public class Laps {

    int lapCounter;
    String lapTime;


    public Laps(int lapCounter, String lapTime){
        this.lapCounter=lapCounter;
        this.lapTime=lapTime;
    }

    public int getLapCounter() {
        return lapCounter;
    }

    public String getLapTime() {
        return lapTime;
    }

    public void setLapCounter(int lapCounter) {
        this.lapCounter = lapCounter;
    }

    public void setLapTime(String lapTime) {
        this.lapTime = lapTime;
    }
}
