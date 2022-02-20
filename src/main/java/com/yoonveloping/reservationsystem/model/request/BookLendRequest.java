package com.yoonveloping.reservationsystem.model.request;

import java.util.List;

public class BookLendRequest {

	private List<Long> bookIds;
	private Long memberId;

	public BookLendRequest(List<Long> bookIds, Long memberId) {
		this.bookIds = bookIds;
		this.memberId = memberId;
	}

	public List<Long> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
