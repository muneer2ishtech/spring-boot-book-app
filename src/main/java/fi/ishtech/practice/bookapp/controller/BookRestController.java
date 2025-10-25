package fi.ishtech.practice.bookapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fi.ishtech.practice.bookapp.dto.BookDto;
import fi.ishtech.practice.bookapp.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Muneer Ahmed Syed
 */
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Slf4j
public class BookRestController {

	private final BookService bookService;

	// @formatter:off
	@Operation(summary = "Create a new book",
			description = "Creates a new book entry and returns the created book details.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Book created successfully",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = BookDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
	})
	// @formatter:on
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookDto create(@RequestBody @Valid BookDto bookDto) {
		return bookService.createAndMapToDto(bookDto);
	}

	// @formatter:off
	@Operation(summary = "Find book by ID", description = "Retrieves the details of a book by its unique ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book found",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = BookDto.class))),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
	})
	// @formatter:on
	@GetMapping("/{id}")
	public BookDto findById(@PathVariable Long id) {
		return bookService.findByIdAndMapToDto(id);
	}

	// @formatter:off
	@Operation(summary = "List all books", description = "Retrieves a list of all available books.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of books retrieved",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = BookDto.class)))
	})
	// @formatter:on
	@GetMapping
	public List<BookDto> findAll() {
		return bookService.findAllAndMapToDto();
	}

	// @formatter:off
	@Operation(summary = "Update book details", description = "Updates an existing book by its ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book updated successfully",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = BookDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
	})
	// @formatter:on
	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateById(@PathVariable Long id, @RequestBody BookDto bookDto) {
		log.trace("Updating Book({})", id);

		Assert.isTrue(bookDto.getId() == null || id == bookDto.getId(), "Input id param not matching with id in DTO");

		var output = bookService.updateByIdAndMapToDto(id, bookDto);
		log.debug("Updated Book({})", id);

		return ResponseEntity.ok(output);
	}

	// @formatter:off
	@Operation(summary = "Delete a book", description = "Deletes a book by its ID permanently.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "410", description = "Book deleted successfully", content = @Content)
	})
	// @formatter:off
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		log.trace("Deleting Book({})", id);

		bookService.deleteById(id);
		log.debug("Deleted Book({})", id);

		return ResponseEntity.status(HttpStatus.GONE).build();
	}

}