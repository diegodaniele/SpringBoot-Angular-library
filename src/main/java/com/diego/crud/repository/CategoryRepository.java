package com.diego.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.crud.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findAllByDescription(String description);
}
