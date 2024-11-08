package dev.patika.LibraryManagementWithAPI.business.concretes;

import dev.patika.LibraryManagementWithAPI.business.abstracts.ICategoryService;
import dev.patika.LibraryManagementWithAPI.core.exception.NotFoundException;
import dev.patika.LibraryManagementWithAPI.core.utility.Messages;
import dev.patika.LibraryManagementWithAPI.dao.BookRepo;
import dev.patika.LibraryManagementWithAPI.dao.CategoryRepo;
import dev.patika.LibraryManagementWithAPI.entity.Book;
import dev.patika.LibraryManagementWithAPI.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements ICategoryService {
    private final CategoryRepo categoryRepo;
    private final BookRepo bookRepo;

    public CategoryManager(CategoryRepo categoryRepo, BookRepo bookRepo) {
        this.categoryRepo = categoryRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND));
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId()); //Control
        return this.categoryRepo.save(category);
    }

    @Override
    public String delete(int id) {
        List<Book> books = bookRepo.findByCategoryListId(id);
        if (!books.isEmpty()) {
            return "There are books belonging to this category. This category could not be deleted.";
        }
        Category category = this.get(id);
        this.categoryRepo.delete(category);
        return "Category deleted successfully.";
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public List<Category> getCategoryIdListByIds(List<Integer> categoryIds) {
        return categoryRepo.findAllById(categoryIds);
    }
}
