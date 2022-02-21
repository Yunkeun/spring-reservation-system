package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Author;
import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.Member;
import com.yoonveloping.reservationsystem.model.request.AuthorCreationRequest;
import com.yoonveloping.reservationsystem.model.request.BookCreationRequest;
import com.yoonveloping.reservationsystem.model.request.BookLendRequest;
import com.yoonveloping.reservationsystem.model.request.MemberCreationRequest;
import java.util.List;

public interface LibraryService {

	Book findBookById(Long id);

	Book findBookByIsbn(String isbn);

	List<Book> findAllBooks();

	void deleteBookById(Long id);

	Book createBook(BookCreationRequest request);

	Member createMember(MemberCreationRequest request);

	Member updateMember(Long id, MemberCreationRequest request);

	Author createAuthor(AuthorCreationRequest request);

	List<String> lendBook(BookLendRequest request);
}
