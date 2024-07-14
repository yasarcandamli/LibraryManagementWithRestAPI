package dev.patika.LibraryManagementWithAPI.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {
    @NotNull(message = "name cannot be empty or null!")
    private String name;
    @NotNull(message = "establishmentYear cannot be empty or null!")
    private Year establishmentYear;
    @NotNull(message = "address cannot be empty or null!")
    private String address;
}
