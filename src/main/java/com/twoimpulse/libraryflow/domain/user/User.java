package com.twoimpulse.libraryflow.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twoimpulse.libraryflow.domain.book.Book;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class User {

    @Nonnull
    @JsonProperty(value = "email")
    private String email;

    @Nonnull
    @JsonProperty(value = "name")
    private String name;

    @Nonnull
    @JsonProperty(value = "state")
    private UserState state;

    @Nullable
    @JsonProperty(value = "current_books")
    private Set<Book> currentCheckedOutBooks;

    @Nullable
    @JsonProperty(value = "all_books")
    private Set<Book> allCheckedOutBook;

    @Nullable
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(value = "registration_date")
    private LocalDate registrationDate;

    @JsonCreator
    public User(@JsonProperty(value = "email") @Nonnull String email,
                @JsonProperty(value = "name") @Nonnull String name,
                @JsonProperty(value = "userState") @Nonnull UserState state,
                @JsonProperty(value = "current_books") @Nullable Set<Book> currentCheckedOutBooks,
                @JsonProperty(value = "all_books") @Nullable Set<Book> allCheckedOutBook,
                @JsonProperty(value = "registration_date") @Nullable LocalDate registrationDate) {
        this.email = email;
        this.name = name;
        this.state = state;
        this.currentCheckedOutBooks = currentCheckedOutBooks;
        this.allCheckedOutBook = allCheckedOutBook;
        this.registrationDate = registrationDate;
    }
}
