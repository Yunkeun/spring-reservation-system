package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Author;
import com.yoonveloping.reservationsystem.model.request.AuthorCreationRequest;

public interface AuthorService {

	Author createAuthor(AuthorCreationRequest request);
}
