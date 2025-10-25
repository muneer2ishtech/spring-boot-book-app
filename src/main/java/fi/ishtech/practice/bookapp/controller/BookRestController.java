package fi.ishtech.practice.bookapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestController {

	private final BookService bookService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookDto create(@RequestBody @Valid BookDto bookDto) {
		return bookService.createAndMapToDto(bookDto);
	}

	@GetMapping("/{id}")
	public BookDto findById(@PathVariable Long id) {
		return bookService.findByIdAndMapToDto(id);
	}

	@GetMapping
	public List<BookDto> findAll() {
		return bookService.findAllAndMapToDto();
	}

	@PutMapping("/{id}")
	public BookDto update(@PathVariable Long id, @RequestBody BookDto bookDto) {
		return bookService.updateByIdAndMapToDto(id, bookDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void delete(@PathVariable Long id) {
		bookService.deleteById(id);
	}

}