package com.demo.service;

import java.util.List;

import com.demo.categorydto.CategoryDto;
import com.demo.categorydto.CategoryResponse;

public interface CategoryService 
{
	public Boolean saveCategory(CategoryDto categoryDto);
	
	public List<CategoryDto> getAllCategory();
	
	public List<CategoryResponse> getActiveCategory();

	public CategoryDto getCategoryById(Integer id);

	public Boolean deleteCategory(Integer id);

}
