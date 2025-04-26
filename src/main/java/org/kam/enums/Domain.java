package org.kam.enums;

public enum Domain {

    FOOD("restaurant");

    private String domain;

    Domain(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
