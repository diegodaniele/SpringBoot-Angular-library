package com.diego.crud.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.crud.domain.Book;
import com.diego.crud.repository.BookRepository;

@Service
public class BookService {
	private BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book save(Book book) {
		if (book.getId() != null && bookRepository.exists(book.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return bookRepository.save(book);
	}

	public Book update(Book book) {
		if (book.getId() != null && !bookRepository.exists(book.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return bookRepository.save(book);
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findOne(Integer id) {
		return bookRepository.findOne(id);
	}

	public void delete(Integer id) {
		bookRepository.delete(id);
	}
}
