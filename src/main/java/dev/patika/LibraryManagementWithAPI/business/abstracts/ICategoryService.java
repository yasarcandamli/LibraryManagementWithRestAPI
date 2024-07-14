package dev.patika.LibraryManagementWithAPI.business.abstracts;

import dev.patika.LibraryManagementWithAPI.entity.Category;
import dev.patika.LibraryManagementWithAPI.entity.Publisher;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Category save(Category category);

    Category get(int id);

    Category update(Category category);

    boolean delete(int id);

    Page<Category> cursor(int page, int pageSize);
}
