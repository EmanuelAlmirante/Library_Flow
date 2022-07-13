package com.twoimpulse.libraryflow.service;

import com.twoimpulse.libraryflow.domain.book.Book;
import com.twoimpulse.libraryflow.domain.book.BookState;
import com.twoimpulse.libraryflow.domain.user.User;
import com.twoimpulse.libraryflow.domain.user.UserState;
import com.twoimpulse.libraryflow.exception.BusinessException;
import com.twoimpulse.libraryflow.repository.book.BookModel;
import com.twoimpulse.libraryflow.repository.book.BookRepository;
import com.twoimpulse.libraryflow.repository.user.UserModel;
import com.twoimpulse.libraryflow.repository.user.UserRepository;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Nonnull
    private final BookRepository bookRepository;

    @Nonnull
    private final UserRepository userRepository;

    @Nonnull
    private final ModelMapper mapper;

    @Nonnull
    private final Clock clock;

    public LibraryService(BookRepository bookRepository, UserRepository userRepository, ModelMapper mapper, Clock clock) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.clock = clock;
    }

    @Nonnull
    public User createUser(@Nonnull User user) {

        String email = user.getEmail();

        if (verifyIfUserExists(email)) {
            throw new BusinessException("User with email " + email + " already exists.");
        }

        UserModel userModel = mapper.map(user, UserModel.class);
        userModel.setRegistrationDate(LocalDate.now(clock));
        userModel = userRepository.save(userModel);

        user = mapper.map(userModel, User.class);

        return user;
    }

    @Nullable
    public List<User> getAllUsers() {

        List<UserModel> users = userRepository.findAll();

        if (!users.isEmpty()) {
            return users.parallelStream().map(userModel -> mapper.map(userModel, User.class)).collect(Collectors.toList());
        }

        return null;
    }

    @Nonnull
    public User getUserByEmail(@Nonnull String email) {

        UserModel user = userRepository.findById(email).orElse(null);

        if (user != null) {
            return mapper.map(user, User.class);
        }

        throw new BusinessException("User with email " + email + " does not exist");
    }

    public void deleteUserByEmail(@Nonnull String email) {

        userRepository.deleteById(email);
    }

    @Nonnull
    public Book createBook(@Nonnull Book book) {

        String isbnCode = book.getIsbnCode();

        if (verifyIfBookExists(isbnCode)) {
            throw new BusinessException("Book with ISBN code " + isbnCode + " already exists.");

        }

        BookModel bookModel = mapper.map(book, BookModel.class);
        bookRepository.save(bookModel);

        return book;
    }

    @Nullable
    public List<Book> getAllBooks() {

        List<BookModel> books = bookRepository.findAll();

        if (!books.isEmpty()) {
            return books.parallelStream().map(bookModel -> mapper.map(bookModel, Book.class)).collect(Collectors.toList());
        }

        return null;
    }

    @Nonnull
    public Book getBookByIsbnCode(@Nonnull String isbnCode) {

        BookModel book = bookRepository.findById(isbnCode).orElse(null);

        if (book != null) {
            return mapper.map(book, Book.class);
        }

        throw new BusinessException("Book with ISBN code " + isbnCode + " does not exist");
    }

    public void deleteBookByIsbnCode(@Nonnull String isbnCode) {

        bookRepository.deleteById(isbnCode);
    }

    public synchronized void checkOutBooks(@Nonnull String email, @Nonnull List<String> isbnCodes) {

        if (verifyIfUserExists(email)) {
            UserModel user = userRepository.findById(email).get();

            if (verifyUserIsActive(user)) {
                for (String isbnCode : isbnCodes) {
                    if (verifyIfBookExists(isbnCode)) {
                        BookModel book = bookRepository.findById(isbnCode).get();

                        if (verifyBookIsAvailable(book)) {

                            book.setState(BookState.UNAVAILABLE);
                            book.setCurrentUser(user);
                            Set<UserModel> allUsers = book.getAllUsers() == null ? new HashSet<>() : book.getAllUsers();
                            allUsers.add(user);

                            Set<BookModel> currentCheckedOutBooks = user.getCurrentCheckedOutBooks() == null ? new HashSet<>() : user.getCurrentCheckedOutBooks();
                            currentCheckedOutBooks.add(book);
                            Set<BookModel> allCheckedOutBooks = user.getAllCheckedOutBooks() == null ? new HashSet<>() : user.getAllCheckedOutBooks();
                            allCheckedOutBooks.add(book);

                            userRepository.save(user);
                            bookRepository.save(book);
                        } else {
                            throw new BusinessException("Book with ISBN code " + isbnCode + " is not available.");
                        }
                    } else {
                        throw new BusinessException("Book with ISBN code " + isbnCode + " does not exist.");
                    }
                }
            } else {
                throw new BusinessException("User with email " + email + " is not active.");
            }
        } else {
            throw new BusinessException("User with email " + email + " does not exist.");
        }
    }

    public synchronized void returnBooks(@Nonnull String email, @Nonnull List<String> isbnCodes) {

        if (verifyIfUserExists(email)) {
            UserModel user = userRepository.findById(email).get();

            if (verifyUserIsActive(user)) {
                for (String isbnCode : isbnCodes) {
                    if (verifyIfBookExists(isbnCode)) {
                        BookModel book = bookRepository.findById(isbnCode).get();

                        if (verifyBookIsAvailable(book)) {

                            book.setState(BookState.AVAILABLE);
                            book.setCurrentUser(null);

                            Set<BookModel> currentCheckedOutBooks = user.getCurrentCheckedOutBooks();
                            currentCheckedOutBooks.remove(book);

                            userRepository.save(user);
                            bookRepository.save(book);
                        } else {
                            throw new BusinessException("Book with ISBN code " + isbnCode + " is not available.");
                        }
                    } else {
                        throw new BusinessException("Book with ISBN code " + isbnCode + " does not exist.");
                    }
                }
            } else {
                throw new BusinessException("User with email " + email + " is not active.");
            }
        } else {
            throw new BusinessException("User with email " + email + " does not exist.");
        }
    }

    private boolean verifyIfUserExists(@Nonnull String email) {

        UserModel userModel = userRepository.findById(email).orElse(null);

        return userModel != null;
    }

    private boolean verifyIfBookExists(@Nonnull String isbnCode) {

        BookModel bookModel = bookRepository.findById(isbnCode).orElse(null);

        return bookModel != null;
    }

    private boolean verifyUserIsActive(@Nonnull UserModel user) {

        return user.getState().equals(UserState.ACTIVE);
    }

    private boolean verifyBookIsAvailable(@Nonnull BookModel book) {

        return book.getState().equals(BookState.AVAILABLE);
    }

}
