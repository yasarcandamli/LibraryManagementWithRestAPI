package dev.patika.LibraryManagementWithAPI.dto.request.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {
    @Positive(message = "id value must be a positive!")
    private int id;
    @NotNull(message = "name cannot be empty or null!")
    private String name;
    @NotNull(message = "description cannot be empty or null!")
    private String description;
}
