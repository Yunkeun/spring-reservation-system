package com.yoonveloping.reservationsystem.service;

import com.yoonveloping.reservationsystem.model.Lend;
import com.yoonveloping.reservationsystem.model.request.BookLendRequest;
import java.util.List;

public interface LendService {

	List<String> lendBook(BookLendRequest request);

	List<Lend> findAllLends();

	Lend findLendById(Long id);
}
