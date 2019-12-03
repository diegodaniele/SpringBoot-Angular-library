package com.diego.crud.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.diego.crud.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diego.crud.domain.Category;

@RestController
@RequestMapping("/api")
public class CategoryResource {
	private CategoryService categoryService;

	public CategoryResource(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(value = "category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getAllCategories() {
		return categoryService.findAll();
	}

	@RequestMapping(value = "category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException {
		Category result = categoryService.save(category);

		return ResponseEntity.created(new URI("/api/category/" + result.getId())).body(result);
	}

	@RequestMapping(value = "category", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws URISyntaxException {
		if (category.getId() == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}

		try {
			Category result = categoryService.update(category);

			return ResponseEntity.created(new URI("/api/category/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
		categoryService.delete(id);

		return ResponseEntity.ok().build();
	}
}
