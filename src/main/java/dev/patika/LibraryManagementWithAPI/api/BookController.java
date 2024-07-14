package dev.patika.LibraryManagementWithAPI.api;

import dev.patika.LibraryManagementWithAPI.business.abstracts.IAuthorService;
import dev.patika.LibraryManagementWithAPI.business.abstracts.IBookService;
import dev.patika.LibraryManagementWithAPI.business.abstracts.ICategoryService;
import dev.patika.LibraryManagementWithAPI.business.abstracts.IPublisherService;
import dev.patika.LibraryManagementWithAPI.core.config.modelMapper.IModelMapperService;
import dev.patika.LibraryManagementWithAPI.core.result.Result;
import dev.patika.LibraryManagementWithAPI.core.result.ResultData;
import dev.patika.LibraryManagementWithAPI.core.utility.ResultHelper;
import dev.patika.LibraryManagementWithAPI.dto.request.book.BookSaveRequest;
import dev.patika.LibraryManagementWithAPI.dto.request.book.BookUpdateRequest;
import dev.patika.LibraryManagementWithAPI.dto.request.publisher.PublisherSaveRequest;
import dev.patika.LibraryManagementWithAPI.dto.response.CursorResponse;
import dev.patika.LibraryManagementWithAPI.dto.response.author.AuthorResponse;
import dev.patika.LibraryManagementWithAPI.dto.response.book.BookResponse;
import dev.patika.LibraryManagementWithAPI.dto.response.category.CategoryResponse;
import dev.patika.LibraryManagementWithAPI.dto.response.publisher.PublisherResponse;
import dev.patika.LibraryManagementWithAPI.entity.Author;
import dev.patika.LibraryManagementWithAPI.entity.Book;
import dev.patika.LibraryManagementWithAPI.entity.Category;
import dev.patika.LibraryManagementWithAPI.entity.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final ICategoryService categoryService;
    private final IPublisherService publisherService;

    public BookController(
            IBookService bookService,
            IModelMapperService modelMapper,
            IAuthorService authorService,
            ICategoryService categoryService,
            IPublisherService publisherService
    ) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);

        List<Category> categories = this.categoryService.getCategoryIdListByIds(bookSaveRequest.getCategoryIdList());
        saveBook.setCategoryList(categories);

        this.bookService.save(saveBook);
        BookResponse bookResponse = this.modelMapper.forResponse().map(saveBook, BookResponse.class);
        bookResponse.setCategoryIdList(saveBook.getCategoryList().stream().map(Category::getId).collect(Collectors.toList()));

        return ResultHelper.created(bookResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);
        bookResponse.setCategoryIdList(book.getCategoryList().stream().map(Category::getId).collect(Collectors.toList()));
        return ResultHelper.success(bookResponse);
    }

    @GetMapping("/{id}/author")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> getAuthor(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book.getAuthor(), AuthorResponse.class));
    }

    @GetMapping("/{id}/publisher")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> getPublisher(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book.getPublisher(), PublisherResponse.class));
    }

    @GetMapping("/{id}/category")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CategoryResponse>> getCategories(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        List<CategoryResponse> categoryResponse = book.getCategoryList().stream()
                .map(category -> this.modelMapper.forResponse().map(category, CategoryResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(categoryResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<BookResponse> bookResponsePage = bookService.cursor(page, pageSize)
                .map(book -> {
                    BookResponse response = modelMapper.forResponse().map(book, BookResponse.class);
                    List<Integer> categoryIdList = book.getCategoryList().stream()
                            .map(category -> category.getId())
                            .collect(Collectors.toList());
                    response.setCategoryIdList(categoryIdList);
                    return response;
                });

        return ResultHelper.cursor(bookResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);

        Author author = this.authorService.get(bookUpdateRequest.getAuthorId());
        updateBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookUpdateRequest.getPublisherId());
        updateBook.setPublisher(publisher);

        List<Category> categories = this.categoryService.getCategoryIdListByIds(bookUpdateRequest.getCategoryIdList());
        updateBook.setCategoryList(categories);

        this.bookService.update(updateBook);
        BookResponse bookResponse = this.modelMapper.forResponse().map(updateBook, BookResponse.class);
        bookResponse.setCategoryIdList(updateBook.getCategoryList().stream().map(Category::getId).collect(Collectors.toList()));

        return ResultHelper.created(bookResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookService.delete(id);
        return ResultHelper.ok();
    }
}
