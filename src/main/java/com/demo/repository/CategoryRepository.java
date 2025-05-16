package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> 
{
	

}