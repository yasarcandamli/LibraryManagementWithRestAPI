package dev.patika.LibraryManagementWithAPI.business.abstracts;

import dev.patika.LibraryManagementWithAPI.entity.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);

    Book get(int id);

    Book update(Book book);

    boolean delete(int id);

    Page<Book> cursor(int page, int pageSize);
}
