package com.yoonveloping.reservationsystem.controller;

import com.yoonveloping.reservationsystem.model.Author;
import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.Member;
import com.yoonveloping.reservationsystem.model.request.AuthorCreationRequest;
import com.yoonveloping.reservationsystem.model.request.BookCreationRequest;
import com.yoonveloping.reservationsystem.model.request.BookLendRequest;
import com.yoonveloping.reservationsystem.model.request.MemberCreationRequest;
import com.yoonveloping.reservationsystem.service.LibraryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LibraryController {

	private final LibraryService libraryService;

	@GetMapping("/book")
	public ResponseEntity readBooks(@RequestParam(required = false) String isbn) {
		if (isbn == null) {
			return ResponseEntity.ok(libraryService.findAllBooks());
		}
		return ResponseEntity.ok(libraryService.findBookByIsbn(isbn));
	}

	@GetMapping("/book/{bookId}")
	public ResponseEntity<Book> readBook(@PathVariable Long bookId) {
		return ResponseEntity.ok(libraryService.findBookById(bookId));
	}

	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody BookCreationRequest request) {
		return ResponseEntity.ok(libraryService.createBook(request));
	}

	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
		libraryService.deleteBookById(bookId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/author")
	public ResponseEntity<Author> createAuthor(
		@RequestBody AuthorCreationRequest authorCreationRequest) {
		return ResponseEntity.ok(libraryService.createAuthor(authorCreationRequest));
	}

	@PostMapping("/member")
	public ResponseEntity<Member> createMember(@RequestBody MemberCreationRequest request) {
		return ResponseEntity.ok(libraryService.createMember(request));
	}

	@PatchMapping("/member/{memberId}")
	public ResponseEntity<Member> updateMember(@RequestBody MemberCreationRequest request,
		@PathVariable Long memberId) {
		return ResponseEntity.ok(libraryService.updateMember(memberId, request));
	}

	@PostMapping("/book/lend")
	public ResponseEntity<List<String>> lendBook(@RequestBody BookLendRequest bookLendRequest) {
		return ResponseEntity.ok(libraryService.lendBook(bookLendRequest));
	}
}
