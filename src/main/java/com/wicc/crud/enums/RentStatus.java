package com.wicc.crud.enums;


public enum RentStatus {
    RENT("Rent"),
    RETURN("Return");

    public String getName() {
        return name;
    }

    private final String name;

    RentStatus(String name) {
        this.name = name;
    }
}
