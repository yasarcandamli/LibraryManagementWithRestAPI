package dev.patika.LibraryManagementWithAPI.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {
    @Positive(message = "id value must be a positive!")
    private int id;
    @NotNull(message = "name cannot be empty or null!")
    private String name;
    @NotNull(message = "establishmentYear cannot be empty or null!")
    private Year establishmentYear;
    @NotNull(message = "address cannot be empty or null!")
    private String address;
}
