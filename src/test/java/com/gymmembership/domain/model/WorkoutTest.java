package com.gymmembership.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkoutTest {

    @Test
    void calculatesWorkoutDuration() {
        Workout workout = new Workout(101, 10, 25);

        assertEquals(15, workout.getDuration());
    }

    @Test
    void rejectsInvalidWorkoutValues() {
        assertThrows(IllegalArgumentException.class,
                () -> new Workout(0, 10, 25));
        assertThrows(IllegalArgumentException.class,
                () -> new Workout(101, 25, 10));
    }
}
