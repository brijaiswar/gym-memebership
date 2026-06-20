package com.gymmembership;

class MembershipStatistics {

    public final int totalMembers;
    public final int totalPaidMembers;
    public final double conversionRate;

    public MembershipStatistics(int totalMembers, int totalPaidMembers, double conversionRate) {
        this.totalMembers = totalMembers;
        this.totalPaidMembers = totalPaidMembers;
        this.conversionRate = conversionRate;
    }

    public int getTotalMembers() { return totalMembers; }
    public int getTotalPaidMembers() { return totalPaidMembers; }
    public double getConversionRate() { return conversionRate; }
}
