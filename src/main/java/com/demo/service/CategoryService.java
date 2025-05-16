package com.demo.service;

import java.util.List;
import com.demo.entity.Category;

public interface CategoryService 
{
	public Boolean saveCategory(Category category);
	
	public List<Category> getAllCategory();

}
