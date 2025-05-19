package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.categorydto.CategoryResponse;
import com.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> 
{


	List<Category> findByIsActiveTrue();
	

}