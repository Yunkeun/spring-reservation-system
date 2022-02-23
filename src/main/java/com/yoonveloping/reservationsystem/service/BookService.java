package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.request.BookCreationRequest;
import java.util.List;

public interface BookService {

	Book findBookById(Long id);

	Book findBookByIsbn(String isbn);

	List<Book> findAllBooks();

	void deleteBookById(Long id);

	Book createBook(BookCreationRequest request);
}
