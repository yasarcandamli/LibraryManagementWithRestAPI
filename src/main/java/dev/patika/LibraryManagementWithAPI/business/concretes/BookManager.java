package dev.patika.LibraryManagementWithAPI.business.concretes;

import dev.patika.LibraryManagementWithAPI.business.abstracts.IBookService;
import dev.patika.LibraryManagementWithAPI.core.exception.NotFoundException;
import dev.patika.LibraryManagementWithAPI.core.utility.Messages;
import dev.patika.LibraryManagementWithAPI.dao.BookRepo;
import dev.patika.LibraryManagementWithAPI.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {
    private final BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book get(int id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND));
    }

    @Override
    public Book update(Book book) {
        this.get(book.getId()); //Control
        return this.bookRepo.save(book);
    }

    @Override
    public boolean delete(int id) {
        Book book = this.get(id);
        this.bookRepo.delete(book);
        return true;
    }

    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookRepo.findAll(pageable);
    }
}
