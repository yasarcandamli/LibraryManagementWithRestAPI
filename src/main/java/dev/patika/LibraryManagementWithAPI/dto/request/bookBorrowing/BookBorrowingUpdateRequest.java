package dev.patika.LibraryManagementWithAPI.dto.request.bookBorrowing;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingUpdateRequest {
    private int id;
    private String borrowerName;
    @Email
    private String borrowerEmail;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private int bookId;
}
