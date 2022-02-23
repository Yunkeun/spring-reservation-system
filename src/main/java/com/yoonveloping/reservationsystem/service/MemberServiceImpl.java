package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Member;
import com.yoonveloping.reservationsystem.model.MemberStatus;
import com.yoonveloping.reservationsystem.model.request.MemberCreationRequest;
import com.yoonveloping.reservationsystem.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	public static final String MEMBER_NOT_FOUND_BY_ID_MESSAGE = "Cannot find any member under given ID!";
	public static final String MEMBER_NOT_FOUND_MESSAGE = "Cannot find the member in the database!";
	public static final String MEMBER_NOT_ACTIVE_ERROR_MESSAGE = "Member is not active to proceed a lending!";
	private final MemberRepository memberRepository;

	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public Member createMember(MemberCreationRequest request) {
		final Member member = new Member();
		BeanUtils.copyProperties(request, member);
		member.setStatus(MemberStatus.ACTIVE);
		return memberRepository.save(member);
	}

	@Override
	public Member searchMember(Long id) {
		Optional<Member> member = memberRepository.findById(id);
		if (member.isPresent()) {
			return member.get();
		}
		throw new EntityNotFoundException(MEMBER_NOT_FOUND_BY_ID_MESSAGE);
	}

	@Override
	public List<Member> searchAllMembers() {
		return memberRepository.findAll();
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
	public void deleteMemberById(Long id) {
		memberRepository.deleteById(id);
	}
}
