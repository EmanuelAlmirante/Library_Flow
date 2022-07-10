package com.twoimpulse.libraryflow.repository.book;

import com.twoimpulse.libraryflow.domain.book.BookState;
import com.twoimpulse.libraryflow.repository.user.UserModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class BookModel {

    @Id
    @Column(name = "isbn_code")
    private String isbnCode;

    @Column(name = "title")
    private String title;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @Column(name = "released_date")
    private LocalDate releasedDate;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "state")
    private BookState state;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = true)
    private UserModel currentUser;

    @ManyToMany(mappedBy = "allCheckedOutBooks")
    private Set<UserModel> allUsers;
}
