package com.api.bookstore.controller.impl;

import com.api.bookstore.controller.AuthorController;
import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
@Slf4j
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;


    @PostMapping("")
    @ApiOperation(
            value = "add a new author",
            notes = "adds a new author with the supplied AuthorDto",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Author created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<Map<String, Long>> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        log.info("request for adding author {}", authorDto);
        Long id = authorService.addAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", id));
    }

    @Override
    @GetMapping("/all")
    @ApiOperation(
            value = "get all authors",
            notes = "gets all authors in the database in a list format",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        log.info("request to get all authors");
        List<AuthorDto> allAuthors = authorService.getAllAuthors();
        return ResponseEntity.ok(allAuthors);
    }

    @Override
    @GetMapping("")
    @ApiOperation(
            value = "get paginated authors",
            notes = "returns authors in page",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of 5 authors"),
            @ApiResponse(responseCode = "400", description = "Bad request on invalid/missing page parameter")
    })
    public ResponseEntity<Page<AuthorDto>> getPaginatedAuthors(@ApiParam(value = "page to retrieve (starts from 0)", required = true)
                                                                   @RequestParam(defaultValue = "0") Integer page) {
        log.info("request to get authors in paginated style (page {})", page);
        Page<AuthorDto> paginatedAuthors = authorService.getPaginatedAuthors(page);
        return ResponseEntity.ok(paginatedAuthors);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(
            value = "get author by the specified id",
            notes = """
                    returns an author corresponding to the entered id.
                    Throws IdException if no author with the entered id is found.
                    """,
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found author"),
            @ApiResponse(responseCode = "404", description = "Invalid Id"),
    })
    public ResponseEntity<AuthorDto> searchAuthorById(@PathVariable("id") Long authorId) {
        log.info("request to search author with id {}", authorId);
        AuthorDto authorDto = authorService.searchAuthorById(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(authorDto);
    }

    @Override
    @GetMapping("/search")
    @ApiOperation(
            value = "search for author(s) by name",
            notes = "returns list of authors corresponding to the entered name.",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of authors corresponding to entered name"),
            @ApiResponse(responseCode = "400", description = "Bad request on invalid/missing name parameter")
    })
    public ResponseEntity<List<AuthorDto>> searchAuthorsByName(@RequestParam("name") String name) {
        log.info("request to search author with name {}", name);
        List<AuthorDto> authorDtos = authorService.searchAuthorsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(authorDtos);
    }

    @Override
    @PutMapping(value = "/{id}")
    @ApiOperation(
            value = "update for author by id",
            notes = "id must exist in the database. AuthorDto must meet the validation constraints.",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated author"),
            @ApiResponse(responseCode = "404", description = "Invalid Id"),
            @ApiResponse(responseCode = "400", description = "Bad request on invalid/missing AuthorDto parameter")
    })
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long authorId,
                                                  @Valid @RequestBody AuthorDto authorDto) {
        log.info("put request for updating author with id {}", authorId);
        AuthorDto updatedAuthor = authorService.updateAuthor(authorId, authorDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAuthor);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "delete author by id",
            notes = "id must exist in the database.",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deleted author"),
            @ApiResponse(responseCode = "404", description = "Invalid id")
    })
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") Long authorId) {
        log.info("request to delete author with id {}", authorId);
        authorService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
