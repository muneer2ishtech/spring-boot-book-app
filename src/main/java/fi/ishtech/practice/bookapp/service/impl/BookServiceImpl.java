package fi.ishtech.practice.bookapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import fi.ishtech.practice.bookapp.dto.BookDto;
import fi.ishtech.practice.bookapp.entity.Book;
import fi.ishtech.practice.bookapp.mapper.BookMapper;
import fi.ishtech.practice.bookapp.repository.BookRepository;
import fi.ishtech.practice.bookapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Service
@Transactional
@RequiredArgsConstructor
@Validated
@Slf4j
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final BookMapper bookMapper;

	private Book findByIdOrThrow(Long id) {
		return bookRepository.findById(id).orElseThrow();
	}

	@Override
	@Transactional(readOnly = true)
	public BookDto findByIdAndMapToDto(Long id) {
		return bookMapper.toBriefDto(findByIdOrThrow(id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDto> findAllAndMapToDto() {
		return bookMapper.toBriefDto(bookRepository.findAll());
	}

	@Override
	public BookDto createAndMapToDto(BookDto bookDto) {
		Book book = bookMapper.toNewEntity(bookDto);

		book = bookRepository.save(book);

		return bookMapper.toBriefDto(book);
	}

	@Override
	public BookDto updateByIdAndMapToDto(Long id, BookDto bookDto) {
		Assert.isTrue(bookDto.getId() == null || id == bookDto.getId(), "Input id param not matching with id in DTO");

		Book book = findByIdOrThrow(id);

		bookMapper.toEntity(bookDto, book);

		book = bookRepository.save(book);

		return bookMapper.toBriefDto(book);
	}

	@Override
	public void deleteById(Long id) {
		bookRepository.deleteById(id);
	}

}