package dev.patika.LibraryManagementWithAPI.api;

import dev.patika.LibraryManagementWithAPI.business.abstracts.IBookBorrowingService;
import dev.patika.LibraryManagementWithAPI.business.abstracts.IBookService;
import dev.patika.LibraryManagementWithAPI.core.config.modelMapper.IModelMapperService;
import dev.patika.LibraryManagementWithAPI.core.result.Result;
import dev.patika.LibraryManagementWithAPI.core.result.ResultData;
import dev.patika.LibraryManagementWithAPI.core.utility.ResultHelper;
import dev.patika.LibraryManagementWithAPI.dto.request.bookBorrowing.BookBorrowingSaveRequest;
import dev.patika.LibraryManagementWithAPI.dto.request.bookBorrowing.BookBorrowingUpdateRequest;
import dev.patika.LibraryManagementWithAPI.dto.response.CursorResponse;
import dev.patika.LibraryManagementWithAPI.dto.response.book.BookResponse;
import dev.patika.LibraryManagementWithAPI.dto.response.bookBorrowing.BookBorrowingResponse;
import dev.patika.LibraryManagementWithAPI.entity.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/bookBorrowings")
public class BookBorrowingController {
    private final IBookBorrowingService bookBorrowingService;
    private final IBookService bookService;
    private final IModelMapperService modelMapper;

    public BookBorrowingController(
            IBookBorrowingService bookBorrowingService,
            IBookService bookService,
            IModelMapperService modelMapper
    ) {
        this.bookBorrowingService = bookBorrowingService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        BookBorrowing saveBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);

        Book book = this.bookService.get(bookBorrowingSaveRequest.getBookId());
        saveBookBorrowing.setBook(book);

        this.bookBorrowingService.save(saveBookBorrowing);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBookBorrowing, BookBorrowingResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") int id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class));
    }

    @GetMapping("/{id}/book")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> getBook(@PathVariable("id") int id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);

        BookResponse bookResponse = modelMapper.forResponse().map(bookBorrowing.getBook(), BookResponse.class);

        List<Integer> categoryIdList = bookBorrowing.getBook().getCategoryList().stream()
                .map(category -> category.getId())
                .collect(Collectors.toList());
        bookResponse.setCategoryIdList(categoryIdList);

        return ResultHelper.success(bookResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<BookBorrowing> bookBorrowingPage = this.bookBorrowingService.cursor(page, pageSize);
        Page<BookBorrowingResponse> bookBorrowingResponsePage = bookBorrowingPage
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class));

        return ResultHelper.cursor(bookBorrowingResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        BookBorrowing updateBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);

        Book book = this.bookService.get(bookBorrowingUpdateRequest.getBookId());
        updateBookBorrowing.setBook(book);

        this.bookBorrowingService.update(updateBookBorrowing);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateBookBorrowing, BookBorrowingResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookBorrowingService.delete(id);
        return ResultHelper.ok();
    }
}
