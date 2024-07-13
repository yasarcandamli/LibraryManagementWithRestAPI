package dev.patika.LibraryManagementWithAPI.dao;

import dev.patika.LibraryManagementWithAPI.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
