package com.twoimpulse.libraryflow.api;

import com.twoimpulse.libraryflow.domain.book.Book;
import com.twoimpulse.libraryflow.domain.user.User;
import com.twoimpulse.libraryflow.service.LibraryService;

import jakarta.annotation.Nonnull;
import javax.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    @Nonnull
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid @Nonnull User user) {

        return libraryService.createUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {

        return libraryService.getAllUsers();
    }
    @GetMapping("/users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByEmail(@PathVariable(value = "email") @Nonnull String email) {

        return libraryService.getUserByEmail(email);
    }

    @DeleteMapping("/users/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByEmail(@PathVariable(value = "email") @Nonnull String email) {

        libraryService.deleteUserByEmail(email);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody @Valid @Nonnull Book book) {

        return libraryService.createBook(book);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("/books/{isbn_code}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookByIsbnCode(@PathVariable(value = "isbn_code") @Nonnull String isbnCode) {

        return libraryService.getBookByIsbnCode(isbnCode);
    }

    @DeleteMapping("/books/{isbn_code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookByIsbnCode(@PathVariable(value = "isbn_code") @Nonnull String isbnCode) {

        libraryService.deleteBookByIsbnCode(isbnCode);
    }

    @PatchMapping("/checkout/users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void checkOutBooks(@PathVariable(value = "email") @Nonnull String email, @RequestParam(value = "book", required = true) @Nonnull List<String> isbnCodes) {

        libraryService.checkOutBooks(email, isbnCodes);
    }

    @PatchMapping("/return/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void returnBooks(@PathVariable(value = "email") @Nonnull String email, @RequestParam(value = "book", required = true) @Nonnull List<String> isbnCodes) {

        libraryService.returnBooks(email, isbnCodes);
    }
}
