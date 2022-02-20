package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

	public static final String ENTITY_NOT_FOUND_BY_ID_MESSAGE = "Cannot find any book under given ID!";
	public static final String ENTITY_NOT_FOUND_BY_ISBN_MESSAGE = "Cannot find any book under given ISBN!";

	private final BookRepository bookRepository;

	public LibraryService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book findBookById(Long id) {
		final Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException(ENTITY_NOT_FOUND_BY_ID_MESSAGE);
	}

	public Book findBookByIsbn(String isbn) {
		final Optional<Book> book = bookRepository.findByIsbn(isbn);
		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException(ENTITY_NOT_FOUND_BY_ISBN_MESSAGE);
	}

	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}
}
