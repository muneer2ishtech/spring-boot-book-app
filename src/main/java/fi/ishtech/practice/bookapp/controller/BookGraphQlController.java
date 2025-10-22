package fi.ishtech.practice.bookapp.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import fi.ishtech.practice.bookapp.entity.Book;
import fi.ishtech.practice.bookapp.service.BookService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookGraphQlController {

	private final BookService bookService;

	@QueryMapping
	public List<Book> books() {
		return bookService.getAll();
	}

	@QueryMapping
	public Book book(@Argument("id") Long id) {
		return bookService.getById(id);
	}

	@MutationMapping
	public Book updateBook(@Argument("id") Long id,
							@Argument("title") String title,
							@Argument("author") String author,
							@Argument("year") Short year,
							@Argument("price") BigDecimal price) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setYear(year);
		book.setPrice(price);

		return bookService.update(id, book);
	}

	@MutationMapping
	public Boolean deleteBook(@Argument("id") Long id) {
		bookService.delete(id);

		return true;
	}

}