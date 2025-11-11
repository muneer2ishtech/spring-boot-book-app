package fi.ishtech.practice.bookapp.service;

import java.util.List;

import fi.ishtech.base.service.BaseStandardService;
import fi.ishtech.practice.bookapp.dto.BookDto;
import fi.ishtech.practice.bookapp.entity.Book;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Service interface for Book related operations
 *
 * @author Muneer Ahmed Syed
 */
public interface BookService extends BaseStandardService<Book, BookDto> {

	/**
	 * Creates new {@link Book} and returns the Dto with ID
	 *
	 * @param book {@link BookDto}
	 * @return {@link BookDto}
	 */
	BookDto createAndMapToDto(@NotNull @Valid BookDto bookDto);

	/**
	 * Finds {@link Book} by id and throws exception if not present
	 *
	 * @param id {@link Long}
	 * @return {@link BookDto}
	 */
	BookDto findByIdAndMapToDto(@NotNull Long id);

	/**
	 * Finds all {@link Book}s
	 *
	 * @return {@link List}&lt;{@link BookDto}&gt;
	 */
	List<BookDto> findAllAndMapToDto();

	/**
	 * Finds by id and updates {@link Book} entity and throws exception if not present
	 *
	 * @param id   {@link Long}
	 * @param book {@link BookDto}
	 * @return {@link BookDto}
	 */
	BookDto updateByIdAndMapToDto(@NotNull Long id, @NotNull @Valid BookDto book);

	/**
	 * Finds by id delete, ignores if not present
	 *
	 * @param id {@link Long}
	 */
	void deleteById(@NotNull Long id);

}