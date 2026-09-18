package com.gymmembership.domain.model;

public class Workout {

    private final int id;
    private final int startTime;
    private final int endTime;

    public Workout(int id, int startTime, int endTime) {
        if (id <= 0) {
            throw new IllegalArgumentException("Workout ID must be positive");
        }
        if (endTime < startTime)
            throw new IllegalArgumentException("Workout endTime cannot be before startTime");

        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() { return id; }
    public int getStartTime() { return startTime; }
    public int getEndTime() { return endTime; }

    public int getDuration() {
        return endTime - startTime;
    }
}
