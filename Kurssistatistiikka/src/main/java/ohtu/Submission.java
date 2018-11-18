package ohtu;

import java.util.Arrays;

public class Submission {
    private int week;
    private int hours;
    private String course;
    private int[] exercises;
    private int maxexercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    public void setMaxexercises(int exemax) {
        this.maxexercises = exemax;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }
    

    @Override
    public String toString() {
        String madeExercises = Arrays.toString(exercises).replace("[", "").replace("]", "");
        return "viikko "+week+":\n  tehtyja tehtavia yhteensa "+exercises.length+"/" + maxexercises + " aikaa kului " + hours + " tehdyt tehtavat: " + madeExercises;
    }
    
}