package com.yoonveloping.reservationsystem.repository;

import com.yoonveloping.reservationsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
