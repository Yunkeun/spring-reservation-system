package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Author;
import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.Lend;
import com.yoonveloping.reservationsystem.model.LendStatus;
import com.yoonveloping.reservationsystem.model.Member;
import com.yoonveloping.reservationsystem.model.MemberStatus;
import com.yoonveloping.reservationsystem.model.request.AuthorCreationRequest;
import com.yoonveloping.reservationsystem.model.request.BookCreationRequest;
import com.yoonveloping.reservationsystem.model.request.BookLendRequest;
import com.yoonveloping.reservationsystem.model.request.MemberCreationRequest;
import com.yoonveloping.reservationsystem.repository.AuthorRepository;
import com.yoonveloping.reservationsystem.repository.BookRepository;
import com.yoonveloping.reservationsystem.repository.LendRepository;
import com.yoonveloping.reservationsystem.repository.MemberRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {

	public static final String BOOK_NOT_FOUND_BY_ID_MESSAGE = "Cannot find any book under given ID!";
	public static final String BOOK_NOT_FOUND_BY_ISBN_MESSAGE = "Cannot find any book under given ISBN!";
	private static final String AUTHOR_NOT_FOUND_MESSAGE = "Cannot find any author!";
	private static final String MEMBER_NOT_FOUND_MESSAGE = "Cannot find the member in the database!";
	private static final String MEMBER_NOT_ACTIVE_ERROR_MESSAGE = "Member is not active to proceed a lending!";
	private static final int EXPIRATION_DAYS = 30;

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final LendRepository lendRepository;
	private final MemberRepository memberRepository;

	public LibraryServiceImpl(BookRepository bookRepository,
		AuthorRepository authorRepository,
		LendRepository lendRepository,
		MemberRepository memberRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.lendRepository = lendRepository;
		this.memberRepository = memberRepository;
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
		BeanUtils.copyProperties(book, request);
		book.setAuthor(author.get());
		return bookRepository.save(book);
	}

	@Override
	public Member createMember(MemberCreationRequest request) {
		final Member member = new Member();
		BeanUtils.copyProperties(member, request);
		return memberRepository.save(member);
	}

	@Override
	public Member updateMember(Long id, MemberCreationRequest request) {
		Optional<Member> memberById = memberRepository.findById(id);
		if (memberById.isEmpty()) {
			throw new EntityNotFoundException(MEMBER_NOT_FOUND_MESSAGE);
		}
		final Member member = memberById.get();
		return memberRepository.save(request.updateMemberName(member));
	}

	@Override
	public Author createAuthor(AuthorCreationRequest request) {
		final Author author = new Author();
		BeanUtils.copyProperties(author, request);
		return authorRepository.save(author);
	}

	@Override
	public List<String> lendBook(BookLendRequest request) {
		return approveBooksToBurrow(request);
	}

	private List<String> approveBooksToBurrow(BookLendRequest request) {
		final List<String> booksApprovedToBurrow = new ArrayList<>();
		request.getBookIds().forEach(bookId
			-> saveLendInfo(booksApprovedToBurrow, findBook(bookId), findMember(request)));
		return booksApprovedToBurrow;
	}

	private Member findMember(BookLendRequest request) {
		final Optional<Member> memberById = memberRepository.findById(
			request.getMemberId());
		if (memberById.isEmpty()) {
			throw new EntityNotFoundException(MEMBER_NOT_FOUND_MESSAGE);
		}
		final Member member = memberById.get();
		checkStatus(member);
		return member;
	}

	private void checkStatus(Member memberForId) {
		if (memberForId.getStatus() != MemberStatus.ACTIVE) {
			throw new RuntimeException(MEMBER_NOT_ACTIVE_ERROR_MESSAGE);
		}
	}

	private Book findBook(Long bookId) {
		final Optional<Book> bookById = bookRepository.findById(bookId);
		if (bookById.isEmpty()) {
			throw new EntityNotFoundException(BOOK_NOT_FOUND_BY_ID_MESSAGE);
		}
		return bookById.get();
	}


	private void saveLendInfo(List<String> booksApprovedToBurrow, Book bookForId,
		Member memberForId) {
		final Optional<Lend> burrowedBook = lendRepository.findByBookAndStatus(bookForId,
			LendStatus.UNAVAILABLE);
		if (burrowedBook.isEmpty()) {
			lendRepository.save(setLendInfo(booksApprovedToBurrow, bookForId, memberForId));
		}
	}

	private Lend setLendInfo(List<String> booksApprovedToBurrow, Book bookForId,
		Member memberForId) {
		booksApprovedToBurrow.add(bookForId.getName());
		final Lend lend = new Lend();
		lend.setMember(memberForId);
		lend.setBook(bookForId);
		lend.setStatus(LendStatus.UNAVAILABLE);
		lend.setStartOn(Instant.now());
		lend.setDueOn(Instant.now().plus(EXPIRATION_DAYS, ChronoUnit.DAYS));
		return lend;
	}
}
