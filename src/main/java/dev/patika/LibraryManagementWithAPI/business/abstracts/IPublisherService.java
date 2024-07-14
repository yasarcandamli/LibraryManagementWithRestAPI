package dev.patika.LibraryManagementWithAPI.business.abstracts;

import dev.patika.LibraryManagementWithAPI.entity.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {
    Publisher save(Publisher publisher);

    Publisher get(int id);

    Publisher update(Publisher publisher);

    boolean delete(int id);

    Page<Publisher> cursor(int page, int pageSize);
}
