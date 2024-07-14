package dev.patika.LibraryManagementWithAPI.business.abstracts;

import dev.patika.LibraryManagementWithAPI.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    Category save(Category category);

    Category get(int id);

    Category update(Category category);

    String delete(int id);

    Page<Category> cursor(int page, int pageSize);

    List<Category> getCategoryIdListByIds (List<Integer> categoryIds);
}
