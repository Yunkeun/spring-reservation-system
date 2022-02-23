package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Member;
import com.yoonveloping.reservationsystem.model.request.MemberCreationRequest;
import java.util.List;

public interface MemberService {

	Member createMember(MemberCreationRequest request);

	Member searchMember(Long id);

	List<Member> searchAllMembers();

	Member updateMember(Long id, MemberCreationRequest request);

	void deleteMemberById(Long id);
}
