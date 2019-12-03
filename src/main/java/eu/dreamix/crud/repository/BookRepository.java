package eu.dreamix.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.dreamix.crud.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	Book findByName(String name);
}
