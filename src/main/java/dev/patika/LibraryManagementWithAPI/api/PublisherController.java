package dev.patika.LibraryManagementWithAPI.api;

import dev.patika.LibraryManagementWithAPI.business.abstracts.IAuthorService;
import dev.patika.LibraryManagementWithAPI.business.abstracts.IPublisherService;
import dev.patika.LibraryManagementWithAPI.core.config.modelMapper.IModelMapperService;
import dev.patika.LibraryManagementWithAPI.core.result.Result;
import dev.patika.LibraryManagementWithAPI.core.result.ResultData;
import dev.patika.LibraryManagementWithAPI.core.utility.ResultHelper;
import dev.patika.LibraryManagementWithAPI.dto.request.author.AuthorSaveRequest;
import dev.patika.LibraryManagementWithAPI.dto.request.author.AuthorUpdateRequest;
import dev.patika.LibraryManagementWithAPI.dto.request.publisher.PublisherSaveRequest;
import dev.patika.LibraryManagementWithAPI.dto.request.publisher.PublisherUpdateRequest;
import dev.patika.LibraryManagementWithAPI.dto.response.CursorResponse;
import dev.patika.LibraryManagementWithAPI.dto.response.author.AuthorResponse;
import dev.patika.LibraryManagementWithAPI.dto.response.publisher.PublisherResponse;
import dev.patika.LibraryManagementWithAPI.entity.Author;
import dev.patika.LibraryManagementWithAPI.entity.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {
    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);
        return ResultHelper.created(this.modelMapper.forResponse().map(savePublisher, PublisherResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") int id) {
        Publisher publisher = this.publisherService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(publisher, PublisherResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Publisher> publisherPage = this.publisherService.cursor(page, pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage
                .map(publisher -> this.modelMapper.forResponse().map(publisher, PublisherResponse.class));

        return ResultHelper.cursor(publisherResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest, Publisher.class);
        this.publisherService.update(updatePublisher);
        return ResultHelper.success(this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }
}
