package dev.patika.LibraryManagementWithAPI.dto.request.author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {
    @Positive(message = "id value must be a positive!")
    private int id;
    @NotNull(message = "name cannot be empty or null!")
    private String name;
    @NotNull(message = "birthDate cannot be empty or null!")
    private String birthDate;
    @NotNull(message = "country cannot be empty or null!")
    private String country;
}
