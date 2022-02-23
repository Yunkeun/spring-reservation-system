package com.yoonveloping.reservationsystem.service;

import static com.yoonveloping.reservationsystem.service.AuthorServiceImpl.AUTHOR_NOT_FOUND_MESSAGE;

import com.yoonveloping.reservationsystem.model.Author;
import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.request.BookCreationRequest;
import com.yoonveloping.reservationsystem.repository.AuthorRepository;
import com.yoonveloping.reservationsystem.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	public static final String BOOK_NOT_FOUND_BY_ID_MESSAGE = "Cannot find any book under given ID!";
	public static final String BOOK_NOT_FOUND_BY_ISBN_MESSAGE = "Cannot find any book under given ISBN!";
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@Override
	public Book findBookById(Long id) {
		final Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException(BOOK_NOT_FOUND_BY_ID_MESSAGE);
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		final Optional<Book> book = bookRepository.findByIsbn(isbn);
		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException(BOOK_NOT_FOUND_BY_ISBN_MESSAGE);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public Book createBook(BookCreationRequest request) {
		final Optional<Author> author = authorRepository.findById(request.getAuthorId());
		if (author.isEmpty()) {
			throw new EntityNotFoundException(AUTHOR_NOT_FOUND_MESSAGE);
		}
		final Book book = new Book();
		BeanUtils.copyProperties(request, book);
		book.setAuthor(author.get());
		return bookRepository.save(book);
	}
}
