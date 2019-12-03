package eu.dreamix.crud.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.dreamix.crud.domain.Book;
import eu.dreamix.crud.service.BookService;

@RestController
@RequestMapping("/api")
public class BookResource {

	private BookService bookService;

	public BookResource(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "book", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getAllBooks() {
		return bookService.findAll();
	}

	@RequestMapping(value = "book", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> createBook(@RequestBody Book book) throws URISyntaxException {
		try {
			Book result = bookService.save(book);
			return ResponseEntity.created(new URI("/api/book/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Book>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "book", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> updateBook(@RequestBody Book book) throws URISyntaxException {
		if (book.getId() == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

		try {
			Book result = bookService.update(book);

			return ResponseEntity.created(new URI("/api/book/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
		bookService.delete(id);

		return ResponseEntity.ok().build();
	}
}
