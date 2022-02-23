package com.yoonveloping.reservationsystem.controller;

import com.yoonveloping.reservationsystem.model.Author;
import com.yoonveloping.reservationsystem.model.request.AuthorCreationRequest;
import com.yoonveloping.reservationsystem.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/library/author")
@CrossOrigin("*")
public class AuthorController {

	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@PostMapping("")
	public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationRequest request) {
		Author author = authorService.createAuthor(request);
		return ResponseEntity.ok(author);
	}
}
