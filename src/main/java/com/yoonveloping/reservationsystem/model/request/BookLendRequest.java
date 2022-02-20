package com.yoonveloping.reservationsystem.model.request;

import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.Lend;
import com.yoonveloping.reservationsystem.model.LendStatus;
import com.yoonveloping.reservationsystem.model.Member;
import com.yoonveloping.reservationsystem.repository.LendRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookLendRequest {

	private static final int EXPIRATION_DAYS = 30;

	private Long bookId;
	private Long memberId;

	public BookLendRequest(Long bookId, Long memberId) {
		this.bookId = bookId;
		this.memberId = memberId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void saveLendInfo(LendRepository lendRepository, List<String> booksApprovedToBurrow, Book bookForId, Member memberForId) {
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
