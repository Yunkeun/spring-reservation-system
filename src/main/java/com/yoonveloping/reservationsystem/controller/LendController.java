package com.yoonveloping.reservationsystem.controller;

import com.yoonveloping.reservationsystem.model.request.BookLendRequest;
import com.yoonveloping.reservationsystem.service.LendService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/lend")
@CrossOrigin("*")
public class LendController {

	private final LendService lendService;

	public LendController(LendService lendService) {
		this.lendService = lendService;
	}

	@PostMapping("")
	public ResponseEntity<List<String>> lendBook(@RequestBody BookLendRequest request) {
		return ResponseEntity.ok(lendService.lendBook(request));
	}
}
