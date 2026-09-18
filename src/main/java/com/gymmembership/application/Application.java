package com.gymmembership.application;

import com.gymmembership.domain.model.Member;
import com.gymmembership.domain.model.MembershipStatus;
import com.gymmembership.domain.service.Membership;

public final class Application {

    private Application() {
    }

    public static void main(String[] args) {
        Membership membership = new Membership();
        membership.addMember(new Member(1, "John Doe", MembershipStatus.SILVER));

        System.out.printf("Gym membership initialized with %d member(s).%n",
                membership.getMembers().size());
    }
}
