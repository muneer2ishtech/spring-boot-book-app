package fi.ishtech.practice.bookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.ishtech.practice.bookapp.entity.Book;

public interface BookRepository  extends JpaRepository<Book, Long> {

}