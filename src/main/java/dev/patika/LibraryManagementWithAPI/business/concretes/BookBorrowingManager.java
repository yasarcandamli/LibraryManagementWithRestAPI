package dev.patika.LibraryManagementWithAPI.business.concretes;

import dev.patika.LibraryManagementWithAPI.business.abstracts.IBookBorrowingService;
import dev.patika.LibraryManagementWithAPI.core.exception.NotFoundException;
import dev.patika.LibraryManagementWithAPI.core.utility.Messages;
import dev.patika.LibraryManagementWithAPI.dao.BookBorrowingRepo;
import dev.patika.LibraryManagementWithAPI.dao.BookRepo;
import dev.patika.LibraryManagementWithAPI.entity.Book;
import dev.patika.LibraryManagementWithAPI.entity.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private final BookBorrowingRepo bookBorrowingRepo;
    private final BookRepo bookRepo;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo, BookRepo bookRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        Book book = bookRepo.findById(bookBorrowing.getBook().getId()).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND));
        if (book.getStock() <= 0) {
            throw new RuntimeException("Out of stock!");
        }
        book.setStock(book.getStock() - 1);
        bookRepo.save(book);
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(int id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND));
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.get(bookBorrowing.getId()); //Control
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public boolean delete(int id) {
        BookBorrowing bookBorrowing = this.get(id);
        this.bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookBorrowingRepo.findAll(pageable);
    }
}
