package dev.patika.LibraryManagementWithAPI.dto.request.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {
    @NotNull(message = "name cannot be empty or null!")
    private String name;
    @NotNull(message = "description cannot be empty or null!")
    private String description;
}
