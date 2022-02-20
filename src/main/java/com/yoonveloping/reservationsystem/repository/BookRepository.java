package com.yoonveloping.reservationsystem.repository;

import com.yoonveloping.reservationsystem.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByIsbn(String isbn);
}
