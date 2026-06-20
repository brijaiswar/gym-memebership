package com.gymmembership;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Solution {
  /*
    This is not a complete test suite, but tests some basic functionality of
    the code and shows how to use it.
  */
    public static void main(String[] args) {
        testMember();
        testMembership();

        System.out.println("All test cases pass!");
    }

    public static void testAddWorkout() {
        System.out.println("Running testAddWorkout");
        Membership testMembership = new Membership();
        Member testMember1 = new Member(12, "John Doe", MembershipStatus.SILVER);
        testMembership.addMember(testMember1);

        Member testMember2 = new Member(22, "Alex Cleeve", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Workout testWorkout1 = new Workout(111, 10, 20);
        Workout testWorkout2 = new Workout(112, 15, 35);
        Workout testWorkout3 = new Workout(113, 20, 25);
        Workout testWorkout99 = new Workout(999, 1, 2);

        assertTrue(testMembership.addWorkout(12, testWorkout1));
        assertTrue(testMembership.addWorkout(22, testWorkout2));
        assertTrue(testMembership.addWorkout(12, testWorkout3));
        assertFalse(testMembership.addWorkout(404, testWorkout99));
    }

    public static void testGetAverageWorkoutDurations() {
        System.out.println("Running testGetAverageWorkoutDurations");
        Membership testMembership = new Membership();
        Member testMember1 = new Member(12, "John Doe", MembershipStatus.SILVER);
        testMembership.addMember(testMember1);

        Member testMember2 = new Member(22, "Alex Cleeve", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Member testMember3 = new Member(31, "Marie Cardiff", MembershipStatus.GOLD);
        testMembership.addMember(testMember3);

        Member testMember4 = new Member(37, "George Costanza", MembershipStatus.SILVER);
        testMembership.addMember(testMember4);

        Workout testWorkout1 = new Workout(101, 10, 20);
        Workout testWorkout2 = new Workout(102, 15, 35);
        Workout testWorkout3 = new Workout(103, 45, 90);
        Workout testWorkout4 = new Workout(104, 100, 155);
        Workout testWorkout5 = new Workout(105, 120, 200);
        Workout testWorkout6 = new Workout(106, 300, 400);
        Workout testWorkout7 = new Workout(107, 1000, 1010);
        Workout testWorkout8 = new Workout(108, 1010, 1045);

        testMembership.addWorkout(12, testWorkout1);
        testMembership.addWorkout(22, testWorkout2);
        testMembership.addWorkout(31, testWorkout3);
        testMembership.addWorkout(12, testWorkout4);
        testMembership.addWorkout(22, testWorkout5);
        testMembership.addWorkout(31, testWorkout6);
        testMembership.addWorkout(12, testWorkout7);
        testMembership.addWorkout(404, testWorkout8);

        Map<Integer, Double> averageDurations = testMembership.getAverageWorkoutDurations();
        assert Math.abs(averageDurations.get(12) - 25.0) < 0.1 :
                "average duration for member 12 should be 25.0, was " + averageDurations.get(12);
        assert Math.abs(averageDurations.get(22) - 50.0) < 0.1 :
                "average duration for member 22 should be 50.0, was " + averageDurations.get(22);
        assert Math.abs(averageDurations.get(31) - 72.5) < 0.1 :
                "average duration for member 31 should be 72.5, was " + averageDurations.get(31);
        assert averageDurations.containsKey(37) && averageDurations.get(37) == null : "average for a member with no workouts should be null";
        assert !averageDurations.containsKey(404) : "workout for non-existent member should not appear in averages";
    }

    public static void testMember() {
        System.out.println("Running testMember");
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        assert testMember.memberId == 1 :
                "Member ID should be 1, was " + testMember.memberId;
        assert testMember.name.equals("John Doe") :
                "Member name should be \"John Doe\", was \"" + testMember.name + "\"";
        assert testMember.membershipStatus == MembershipStatus.BRONZE :
                "Membership status should be BRONZE, was " + testMember.membershipStatus;
    }

    public static void testMembership() {
        System.out.println("Running testMembership");
        Membership testMembership = new Membership();
        Member testMember = new Member(1, "John Doe", MembershipStatus.BRONZE);
        testMembership.addMember(testMember);
        assert testMembership.members.size() == 1 :
                "Members size should be 1, was " + testMembership.members.size();
        assert testMembership.members.get(0).equals(testMember) :
                "First member should equal testMember";

        testMembership.updateMembership(1, MembershipStatus.SILVER);
        assert testMembership.members.get(0).membershipStatus == MembershipStatus.SILVER :
                "Membership status should be SILVER, was " + testMembership.members.get(0).membershipStatus;

        Member testMember2 = new Member(2, "Alex C", MembershipStatus.BRONZE);
        testMembership.addMember(testMember2);

        Member testMember3 = new Member(3, "Marie C", MembershipStatus.GOLD);
        testMembership.addMember(testMember3);

        Member testMember4 = new Member(4, "Joe D", MembershipStatus.SILVER);
        testMembership.addMember(testMember4);

        Member testMember5 = new Member(5, "June R", MembershipStatus.BRONZE);
        testMembership.addMember(testMember5);

        Member testMember6 = new Member(6, "Westley D", MembershipStatus.SILVER);
        testMembership.addMember(testMember6);

        MembershipStatistics attendanceStats = testMembership.getMembershipStatistics();
        assert attendanceStats.totalMembers == 6 :
                "Total members should be 6, was " + attendanceStats.totalMembers;
        assert attendanceStats.totalPaidMembers == 4 :
                "Total paid members should be 4, was " + attendanceStats.totalPaidMembers;
        assert Math.abs(attendanceStats.conversionRate - 66.67) < 0.1 :
                "Conversion rate should be 66.67, was " + attendanceStats.conversionRate;
    }
}