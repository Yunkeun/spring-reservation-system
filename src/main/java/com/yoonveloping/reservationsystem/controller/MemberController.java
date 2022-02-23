package com.yoonveloping.reservationsystem.controller;

import com.yoonveloping.reservationsystem.model.Member;
import com.yoonveloping.reservationsystem.model.request.MemberCreationRequest;
import com.yoonveloping.reservationsystem.service.MemberService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/member")
@CrossOrigin("*")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("")
	public ResponseEntity<Member> createMember(@RequestBody MemberCreationRequest request) {
		return ResponseEntity.ok(memberService.createMember(request));
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<Member> searchMember(@PathVariable Long memberId) {
		return ResponseEntity.ok(memberService.searchMember(memberId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Member>> searchAllMembers() {
		return ResponseEntity.ok(memberService.searchAllMembers());
	}

	@PatchMapping("/update/{memberId}")
	public ResponseEntity<Member> updateMember(@RequestBody MemberCreationRequest request, @PathVariable Long memberId) {
		return ResponseEntity.ok(memberService.updateMember(memberId, request));
	}

	@DeleteMapping("/delete/{memberId}")
	public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
		memberService.deleteMemberById(memberId);
		return ResponseEntity.ok().build();
	}
}
