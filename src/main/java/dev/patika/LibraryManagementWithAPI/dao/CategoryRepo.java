package dev.patika.LibraryManagementWithAPI.dao;

import dev.patika.LibraryManagementWithAPI.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
