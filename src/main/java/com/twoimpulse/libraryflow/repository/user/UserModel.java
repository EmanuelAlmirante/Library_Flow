package com.twoimpulse.libraryflow.repository.user;

import com.twoimpulse.libraryflow.domain.user.UserState;
import com.twoimpulse.libraryflow.repository.book.BookModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "user")
public class UserModel {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private UserState state;

    @OneToMany(mappedBy = "currentUser")
    private Set<BookModel> currentCheckedOutBooks;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_all_checked_out_books",
        joinColumns = @JoinColumn(name = "book_isbn_code"),
        inverseJoinColumns = @JoinColumn(name = "user_email"))
    private Set<BookModel> allCheckedOutBooks;

    @Column(name = "registration_date")
    private LocalDate registrationDate;
}
