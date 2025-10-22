package fi.ishtech.practice.bookapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.ishtech.practice.bookapp.entity.Book;
import fi.ishtech.practice.bookapp.repository.BookRepository;
import fi.ishtech.practice.bookapp.service.BookService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public Book create(Book book) {
		return bookRepository.save(book);
	}

	@Override
	@Transactional(readOnly = true)
	public Book getById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book update(Long id, Book book) {
		Book existing = getById(id);
		existing.setTitle(book.getTitle());
		existing.setAuthor(book.getAuthor());
		existing.setYear(book.getYear());
		existing.setPrice(book.getPrice());
		return bookRepository.save(existing);
	}

	@Override
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

}