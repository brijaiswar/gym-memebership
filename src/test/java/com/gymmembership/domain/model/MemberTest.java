package com.gymmembership.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberTest {

    @Test
    void createsMemberWithValidatedValues() {
        Member member = new Member(1, "  John Doe  ", MembershipStatus.BRONZE);

        assertEquals(1, member.getMemberId());
        assertEquals("John Doe", member.getName());
        assertEquals(MembershipStatus.BRONZE, member.getMembershipStatus());
    }

    @Test
    void rejectsInvalidMemberValues() {
        assertThrows(IllegalArgumentException.class,
                () -> new Member(0, "John Doe", MembershipStatus.BRONZE));
        assertThrows(IllegalArgumentException.class,
                () -> new Member(1, " ", MembershipStatus.BRONZE));
        assertThrows(IllegalArgumentException.class,
                () -> new Member(1, "John Doe", null));
    }
}
