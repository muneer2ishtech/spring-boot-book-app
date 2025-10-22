package fi.ishtech.practice.bookapp.service;

import java.util.List;

import fi.ishtech.practice.bookapp.entity.Book;

public interface BookService {

	Book create(Book book);

	Book getById(Long id);

	List<Book> getAll();

	Book update(Long id, Book book);

	void delete(Long id);

}