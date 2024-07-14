package dev.patika.LibraryManagementWithAPI.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private int id;
    private String name;
    private Year publicationYear;
    private int stock;
    private int authorId;
    private int publisherId;
    private List<Integer> categoryIdList;
}
