package com.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.demo.categorydto.CategoryDto;
import com.demo.categorydto.CategoryResponse;
import com.demo.entity.Category;
import com.demo.repository.CategoryRepository;
import com.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Boolean saveCategory(CategoryDto categoryDto) 
	{ 
//		
//		Category category = new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setIsActive(categoryDto.getIsActive());
		
		Category category = mapper.map(categoryDto, Category.class);
		
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category save = categoryRepository.save(category);
		if(ObjectUtils.isEmpty(save))
		{
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() 
	{
		List<Category> all = categoryRepository.findAll();
		List<CategoryDto> list = all.stream().map(cat->mapper.map(cat, CategoryDto.class)).toList();
		return list;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() 
	{
		List<Category> category = categoryRepository.findByIsActiveTrue();
		List<CategoryResponse> list = category.stream().map(cat->mapper.map(cat, CategoryResponse.class)).toList();
		return list;
	}

}