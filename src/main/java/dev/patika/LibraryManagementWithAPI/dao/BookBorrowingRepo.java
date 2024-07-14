package dev.patika.LibraryManagementWithAPI.dao;

import dev.patika.LibraryManagementWithAPI.entity.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepo extends JpaRepository<BookBorrowing, Integer> {
}
