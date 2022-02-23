package com.yoonveloping.reservationsystem.controller;

import com.yoonveloping.reservationsystem.model.Lend;
import com.yoonveloping.reservationsystem.model.request.BookLendRequest;
import com.yoonveloping.reservationsystem.service.LendService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/all")
	public ResponseEntity<List<Lend>> findAllLendStatus() {
		return ResponseEntity.ok(lendService.findAllLends());
	}

	@GetMapping("/{lendId}")
	public ResponseEntity<Lend> findLendStatusById(@PathVariable Long lendId) {
		return ResponseEntity.ok(lendService.findLendById(lendId));
	}
}
