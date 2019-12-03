package com.diego.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.crud.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	Book findByName(String name);
}
