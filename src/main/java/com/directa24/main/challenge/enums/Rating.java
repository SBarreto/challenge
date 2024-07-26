package com.directa24.main.challenge.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {

    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC_17"),
    NOT_RATED("Not Rated");

    private final String code;

    Rating(String code) {
        this.code = code;
    }

    @JsonValue
    String getCode() {
        return code;
    }

    @JsonCreator
    public static Rating fromValue(String value) {
        for (Rating rating : values()) {
            String currentContact = rating.getCode();
            if (currentContact.equals(value)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Invalid value for Contact type Enum: " + value);
    }
}
