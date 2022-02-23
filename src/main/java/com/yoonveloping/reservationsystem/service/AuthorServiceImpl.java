package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Author;
import com.yoonveloping.reservationsystem.model.request.AuthorCreationRequest;
import com.yoonveloping.reservationsystem.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

	public static final String AUTHOR_NOT_FOUND_MESSAGE = "Cannot find any author!";
	private final AuthorRepository authorRepository;

	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public Author createAuthor(AuthorCreationRequest request) {
		final Author author = new Author();
		BeanUtils.copyProperties(request, author);
		return authorRepository.save(author);
	}
}
