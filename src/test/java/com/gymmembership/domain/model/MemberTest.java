package com.gymmembership.domain.model;

import com.gymmembership.domain.service.Membership;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void addsAndUpdatesMembers() {
        Membership membership = new Membership();
        Member member = new Member(1, "John Doe", MembershipStatus.BRONZE);

        membership.addMember(member);

        assertEquals(member, membership.getMember(1));
        assertTrue(membership.updateMembership(1, MembershipStatus.SILVER));
        assertEquals(MembershipStatus.SILVER, member.getMembershipStatus());
        assertFalse(membership.updateMembership(404, MembershipStatus.GOLD));
    }

    @Test
    void rejectsDuplicateMembers() {
        Membership membership = new Membership();
        membership.addMember(new Member(1, "John Doe", MembershipStatus.BRONZE));

        assertThrows(IllegalArgumentException.class,
                () -> membership.addMember(new Member(1, "Jane Doe", MembershipStatus.GOLD)));
    }

    @Test
    void calculatesMembershipStatisticsIncludingEmptyMembership() {
        Membership empty = new Membership();
        MembershipStatistics emptyStatistics = empty.getMembershipStatistics();

        assertEquals(0, emptyStatistics.getTotalMembers());
        assertEquals(0, emptyStatistics.getTotalPaidMembers());
        assertEquals(0.0, emptyStatistics.getConversionRate());

        empty.addMember(new Member(1, "John Doe", MembershipStatus.BRONZE));
        empty.addMember(new Member(2, "Jane Doe", MembershipStatus.SILVER));
        empty.addMember(new Member(3, "Alex Doe", MembershipStatus.GOLD));

        MembershipStatistics statistics = empty.getMembershipStatistics();
        assertEquals(3, statistics.getTotalMembers());
        assertEquals(2, statistics.getTotalPaidMembers());
        assertEquals(66.67, statistics.getConversionRate(), 0.01);
    }

    @Test
    void recordsWorkoutsOnlyForExistingMembersAndCalculatesAverages() {
        Membership membership = new Membership();
        membership.addMember(new Member(1, "John Doe", MembershipStatus.SILVER));
        membership.addMember(new Member(2, "Jane Doe", MembershipStatus.BRONZE));

        assertTrue(membership.addWorkout(1, new Workout(101, 10, 20)));
        assertTrue(membership.addWorkout(1, new Workout(102, 30, 60)));
        assertFalse(membership.addWorkout(404, new Workout(103, 0, 10)));
        assertFalse(membership.addWorkout(1, null));

        Map<Integer, Double> averages = membership.getAverageWorkoutDurations();
        assertEquals(20.0, averages.get(1));
        assertTrue(averages.containsKey(2));
        assertNull(averages.get(2));
    }
}
