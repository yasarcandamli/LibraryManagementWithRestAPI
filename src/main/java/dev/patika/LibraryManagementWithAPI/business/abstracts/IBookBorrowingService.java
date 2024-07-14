package dev.patika.LibraryManagementWithAPI.business.abstracts;

import dev.patika.LibraryManagementWithAPI.entity.Book;
import dev.patika.LibraryManagementWithAPI.entity.BookBorrowing;
import org.springframework.data.domain.Page;

public interface IBookBorrowingService {
    BookBorrowing save(BookBorrowing bookBorrowing);

    BookBorrowing get(int id);

    BookBorrowing update(BookBorrowing bookBorrowing);

    boolean delete(int id);

    Page<BookBorrowing> cursor(int page, int pageSize);
}
