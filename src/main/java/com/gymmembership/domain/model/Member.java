package com.gymmembership.domain.model;

public class Member {

    public final int memberId;
    public String name;
    public MembershipStatus membershipStatus;

    public Member(int memberId, String name, MembershipStatus membershipStatus) {
        if (memberId <= 0) {
            throw new IllegalArgumentException("Member ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Member name cannot be blank");
        }
        if (membershipStatus == null) {
            throw new IllegalArgumentException("Membership status cannot be null");
        }

        this.memberId = memberId;
        this.name = name.trim();
        this.membershipStatus = membershipStatus;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public void updateStatus(MembershipStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Membership status cannot be null");
        }
        this.membershipStatus = newStatus;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + memberId +
                ", name='" + name + '\'' +
                ", status=" + membershipStatus +
                '}';
    }
}
