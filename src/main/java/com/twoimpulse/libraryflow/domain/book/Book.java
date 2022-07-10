package com.twoimpulse.libraryflow.domain.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twoimpulse.libraryflow.domain.user.User;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Book {

    @Nonnull
    @JsonProperty(value = "isbn_code")
    private String isbnCode;

    @Nonnull
    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "number_of_pages")
    private int numberOfPages;

    @Nonnull
    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;

    @Nonnull
    @JsonProperty(value = "publisher")
    private String publisher;

    @Nonnull
    @JsonProperty(value = "book_state")
    private BookState state;

    @Nullable
    @JsonProperty(value = "current_user")
    private User currentUser;

    @Nullable
    @JsonProperty(value = "all_users")
    private List<User> allUsers;

    @JsonCreator
    public Book(@JsonProperty(value = "isbn_code") @Nonnull String isbnCode,
                @JsonProperty(value = "title") @Nonnull String title,
                @JsonProperty(value = "number_of_pages") int numberOfPages,
                @JsonProperty(value = "release_date") @Nonnull LocalDate releaseDate,
                @JsonProperty(value = "publisher") @Nonnull String publisher,
                @JsonProperty(value = "book_state") @Nonnull BookState state,
                @JsonProperty(value = "current_user") @Nullable User currentUser,
                @JsonProperty(value = "all_users") @Nullable List<User> allUsers) {
        this.isbnCode = isbnCode;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.state = state;
        this.currentUser = currentUser;
        this.allUsers = allUsers;
    }
}
