package com.gymmembership;

class Member {

    public final int memberId;
    public String name;
    public MembershipStatus membershipStatus;

    public Member(int memberId, String name, MembershipStatus membershipStatus) {
        this.memberId = memberId;
        this.name = name;
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

