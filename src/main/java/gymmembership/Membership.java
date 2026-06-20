package com.gymmembership;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Membership {

    final Map<Integer, Member> members;
    private final Map<Integer, List<Workout>> workoutsByMember;

    public Membership() {
        this.members = new HashMap<>();
        this.workoutsByMember = new HashMap<>();
    }

    // -------------------------------
    // Member Management
    // -------------------------------
    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
        workoutsByMember.putIfAbsent(member.getMemberId(), new ArrayList<>());
    }

    public void updateMembership(int memberId, MembershipStatus newStatus) {
        Member m = members.get(memberId);
        if (m != null) {
            m.updateStatus(newStatus);
        }
    }

    // -------------------------------
    // Membership Statistics
    // -------------------------------
    public MembershipStatistics getMembershipStatistics() {
        int total = members.size();

        long paid = members.values().stream()
                .filter(m -> m.getMembershipStatus() == MembershipStatus.GOLD
                        || m.getMembershipStatus() == MembershipStatus.SILVER)
                .count();

        double conversionRate = (paid * 100.0) / total;

        return new MembershipStatistics(total, (int) paid, conversionRate);
    }

    // -------------------------------
    // Workout Management
    // -------------------------------
    public boolean addWorkout(int memberId, Workout workout) {
        if (!members.containsKey(memberId)) return false;

        workoutsByMember
                .computeIfAbsent(memberId, k -> new ArrayList<>())
                .add(workout);

        return true;
    }

    public Map<Integer, Double> getAverageWorkoutDurations() {
        Map<Integer, Double> result = new HashMap<>();

        for (Member m : members.values()) {
            List<Workout> list = workoutsByMember.get(m.getMemberId());

            if (list == null || list.isEmpty()) {
                result.put(m.getMemberId(), null);
            } else {
                double avg = list.stream()
                        .mapToInt(Workout::getDuration)
                        .average()
                        .orElse(0.0);

                result.put(m.getMemberId(), avg);
            }
        }

        return result;
    }
}
