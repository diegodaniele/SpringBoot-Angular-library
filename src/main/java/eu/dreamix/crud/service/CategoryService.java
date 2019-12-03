package eu.dreamix.crud.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.dreamix.crud.domain.Category;
import eu.dreamix.crud.repository.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category save(Category category) {
		if (category.getId() != null && categoryRepository.exists(category.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return categoryRepository.save(category);
	}

	public Category update(Category category) {
		if (category.getId() != null && !categoryRepository.exists(category.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return categoryRepository.save(category);
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findOne(Integer id) {
		return categoryRepository.findOne(id);
	}

	public void delete(Integer id) {
		categoryRepository.delete(id);
	}
}
