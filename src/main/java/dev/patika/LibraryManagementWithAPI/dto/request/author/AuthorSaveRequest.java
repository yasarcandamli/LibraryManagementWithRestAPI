package dev.patika.LibraryManagementWithAPI.dto.request.author;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {
    @NotNull(message = "name cannot be empty or null!")
    private String name;
    @NotNull(message = "birthDate cannot be empty or null!")
    private String birthDate;
    @NotNull(message = "country cannot be empty or null!")
    private String country;
}
