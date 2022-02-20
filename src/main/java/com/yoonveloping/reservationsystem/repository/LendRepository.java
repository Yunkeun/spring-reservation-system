package com.yoonveloping.reservationsystem.repository;

import com.yoonveloping.reservationsystem.model.Book;
import com.yoonveloping.reservationsystem.model.Lend;
import com.yoonveloping.reservationsystem.model.LendStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendRepository extends JpaRepository<Lend, Long> {

	Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
