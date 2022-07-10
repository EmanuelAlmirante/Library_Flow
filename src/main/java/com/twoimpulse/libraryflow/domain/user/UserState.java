package com.twoimpulse.libraryflow.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserState {
    ACTIVE("active"),
    INACTIVE("inactive");

    private final String userState;

    UserState(String userState) {
        this.userState = userState;
    }

    @JsonCreator
    public static UserState fromString(String userState) {
        return userState == null ? null : UserState.valueOf(userState.toUpperCase());
    }

    @JsonValue
    public String getUserState() {
        return userState;
    }
}
