package ohtu;

import java.util.Arrays;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String name;
    private String term;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int getHours() {
        return hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public String getName() {
        return name;
    }

    public String getTerm() {
        return term;
    }
    
    

    @Override
    public String toString() {
        return "viikko " + week + ":\n   tehtyjä tehtäviä yhteensä: " + exercises.length + ", aikaa kului " + hours + " tuntia, tehdyt tehtävät: " + Arrays.toString(exercises).replaceAll("\\[|\\]", "");
    }
    
}