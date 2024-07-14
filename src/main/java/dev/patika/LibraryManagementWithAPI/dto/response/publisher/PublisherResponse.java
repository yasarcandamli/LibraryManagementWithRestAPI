package dev.patika.LibraryManagementWithAPI.dto.response.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {
    private int id;
    private String name;
}
