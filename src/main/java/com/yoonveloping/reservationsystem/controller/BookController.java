package com.yoonveloping.reservationsystem.controller;

import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.request.BookCreationRequest;
import com.yoonveloping.reservationsystem.service.BookService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/library/book")
@CrossOrigin("*")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("")
	public ResponseEntity<Book> searchBookByIsbn(@RequestParam(required = false) String isbn) {
		return ResponseEntity.ok(bookService.findBookByIsbn(isbn));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Book>> searchAllBooks() {
		return ResponseEntity.ok(bookService.findAllBooks());
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<Book> searchBookById(@PathVariable Long bookId) {
		return ResponseEntity.ok(bookService.findBookById(bookId));
	}

	@PostMapping("")
	public ResponseEntity<Book> createBook(@RequestBody BookCreationRequest request) {
		return ResponseEntity.ok(bookService.createBook(request));
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
		bookService.deleteBookById(bookId);
		return ResponseEntity.ok().build();
	}


}
