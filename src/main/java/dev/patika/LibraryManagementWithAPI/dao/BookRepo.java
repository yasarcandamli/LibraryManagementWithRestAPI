package dev.patika.LibraryManagementWithAPI.dao;

import dev.patika.LibraryManagementWithAPI.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByCategoryListId(int categoryId);
}
