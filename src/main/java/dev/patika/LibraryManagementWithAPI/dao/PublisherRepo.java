package dev.patika.LibraryManagementWithAPI.dao;

import dev.patika.LibraryManagementWithAPI.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
}
