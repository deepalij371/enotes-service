package com.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.demo.exception.ExitDataException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.demo.categorydto.CategoryDto;
import com.demo.categorydto.CategoryResponse;
import com.demo.entity.Category;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.CategoryRepository;
import com.demo.service.CategoryService;
import com.demo.util.Validation;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public Boolean saveCategory(CategoryDto categoryDto) 
	{ 

		validation.categoryValidation(categoryDto);
		
		Boolean exist = categoryRepository.existsByName(categoryDto.getName().trim());
		
		if(exist)
		{
			throw new ExitDataException("category already exits");
		}
		
		Category category = mapper.map(categoryDto, Category.class);
		
		if(ObjectUtils.isEmpty(category.getId()))
		{
			category.setIsDeleted(false);
//			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
		}
		else {
			updateCategory(category);
		}
		
		Category save = categoryRepository.save(category);
		if(ObjectUtils.isEmpty(save))
		{
			return false;
		}
		return true;
	}

	
	
	private void updateCategory(Category category) {
		
		Optional<Category> findbyId = categoryRepository.findById(category.getId());
		if(findbyId.isPresent())
		{
			Category category2 = findbyId.get();
			category.setCreatedBy(category2.getCreatedBy());
			category.setCreatedOn(category2.getCreatedOn());
			category.setIsDeleted(category2.getIsDeleted());
//			category.setUpdatedBy(1);
//			category.setUpdatedOn(new Date());
		}
	}

	@Override
	public List<CategoryDto> getAllCategory() 
	{
		List<Category> all = categoryRepository.findByIsDeletedFalse();
		List<CategoryDto> list = all.stream().map(cat->mapper.map(cat, CategoryDto.class)).toList();
		return list;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() 
	{
		List<Category> category = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
		List<CategoryResponse> list = category.stream().map(cat->mapper.map(cat, CategoryResponse.class)).toList();
		return list;
	}

	
	@Override
	public CategoryDto getCategoryById(Integer id) throws Exception
	{
		Category category = categoryRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()->new ResourceNotFoundException("Category not found"+id));
		
		if(ObjectUtils.isEmpty(category))
		{
//			if(category.getName() == null)
//			{
//				throw new IllegalArgumentException("name is null");
//			}
			category.getName().toUpperCase();
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}

	
	@Override
	public Boolean deleteCategory(Integer id)
	{
		Optional<Category> byId = categoryRepository.findById(id);
		
		if(byId.isPresent())
		{
			Category category = byId.get();
			category.setIsDeleted(true);
			categoryRepository.save(category);
			return true;
		}
		return false;
	}

}