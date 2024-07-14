package dev.patika.LibraryManagementWithAPI.business.concretes;

import dev.patika.LibraryManagementWithAPI.business.abstracts.ICategoryService;
import dev.patika.LibraryManagementWithAPI.core.exception.NotFoundException;
import dev.patika.LibraryManagementWithAPI.core.utility.Messages;
import dev.patika.LibraryManagementWithAPI.dao.CategoryRepo;
import dev.patika.LibraryManagementWithAPI.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
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
    public boolean delete(int id) {
        Category category = this.get(id);
        this.categoryRepo.delete(category);
        return true;
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.categoryRepo.findAll(pageable);
    }
}
