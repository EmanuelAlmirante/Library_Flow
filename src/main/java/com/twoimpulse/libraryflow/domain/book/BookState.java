package com.twoimpulse.libraryflow.domain.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookState {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable");

    private final String bookState;

    BookState(String bookState) {
        this.bookState = bookState;
    }

    @JsonCreator
    public static BookState fromString(String bookState) {
        return bookState == null ? null : BookState.valueOf(bookState.toUpperCase());
    }

    @JsonValue
    public String getBookState() {
        return bookState;
    }
}
