package com.yoonveloping.reservationsystem.service;

import static com.yoonveloping.reservationsystem.service.MemberServiceImpl.*;
import static com.yoonveloping.reservationsystem.service.BookServiceImpl.*;

import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.Lend;
import com.yoonveloping.reservationsystem.model.LendStatus;
import com.yoonveloping.reservationsystem.model.Member;
import com.yoonveloping.reservationsystem.model.MemberStatus;
import com.yoonveloping.reservationsystem.model.request.BookLendRequest;
import com.yoonveloping.reservationsystem.repository.BookRepository;
import com.yoonveloping.reservationsystem.repository.LendRepository;
import com.yoonveloping.reservationsystem.repository.MemberRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LendServiceImpl implements LendService {

	private static final int EXPIRATION_DAYS = 30;
	public static final String LEND_NOT_FOUND_BY_ID_MESSAGE = "Cannot find any lend info under given ID!";
	private final LendRepository lendRepository;
	private final MemberRepository memberRepository;
	private final BookRepository bookRepository;

	public LendServiceImpl(LendRepository lendRepository, MemberRepository memberRepository, BookRepository bookRepository) {
		this.lendRepository = lendRepository;
		this.memberRepository = memberRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public List<String> lendBook(BookLendRequest request) {
		return approveBooksToBurrow(request);
	}

	@Override
	public List<Lend> findAllLends() {
		return lendRepository.findAll();
	}

	@Override
	public Lend findLendById(Long id) {
		final Optional<Lend> lend = lendRepository.findById(id);
		if (lend.isPresent()) {
			return lend.get();
		}
		throw new EntityNotFoundException(LEND_NOT_FOUND_BY_ID_MESSAGE);
	}

	private List<String> approveBooksToBurrow(BookLendRequest request) {
		final List<String> booksApprovedToBurrow = new ArrayList<>();
		request.getBookIds().forEach(bookId
			-> saveLendInfo(booksApprovedToBurrow, findBook(bookId), findMember(request)));
		return booksApprovedToBurrow;
	}

	private Member findMember(BookLendRequest request) {
		final Optional<Member> memberById = memberRepository.findById(request.getMemberId());
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

	private void saveLendInfo(List<String> booksApprovedToBurrow, Book bookForId, Member memberForId) {
		final Optional<Lend> burrowedBook = lendRepository.findByBookAndStatus(bookForId, LendStatus.UNAVAILABLE);
		if (burrowedBook.isEmpty()) {
			lendRepository.save(setLendInfo(booksApprovedToBurrow, bookForId, memberForId));
		}
	}

	private Lend setLendInfo(List<String> booksApprovedToBurrow, Book bookForId, Member memberForId) {
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
