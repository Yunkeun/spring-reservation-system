package com.yoonveloping.reservationsystem.model.request;

import com.yoonveloping.reservationsystem.model.Member;

public class MemberCreationRequest {

	private String firstName;
	private String lastName;

	public MemberCreationRequest(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Member updateMemberName(Member member) {
		member.setLastName(lastName);
		member.setFirstName(firstName);
		return member;
	}
}
