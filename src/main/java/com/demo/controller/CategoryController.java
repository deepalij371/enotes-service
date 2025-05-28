package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.categorydto.CategoryDto;
import com.demo.categorydto.CategoryResponse;
import com.demo.exception.ResourceNotFoundException;
import com.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController 
{
	@Autowired
	private CategoryService  CategoryService;
	
	@PostMapping("/savecategory")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto)
	{
		
		Boolean saveCategory = CategoryService.saveCategory(categoryDto);
		if(saveCategory) {
		return new ResponseEntity<>("saved success",HttpStatus.CREATED);
	    }
		else {
			return new ResponseEntity<>("not saved",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getcategory")
	public ResponseEntity<?> getAllCategory()
	{
		List<CategoryDto> allCategory = CategoryService.getAllCategory();
		if(CollectionUtils.isEmpty(allCategory))
		{
			return ResponseEntity.noContent().build();
		}
		else 
		{
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}
	
	@GetMapping("/active-category")
	public ResponseEntity<?> getActiveCategory()
	{
		List<CategoryResponse> allCategory = CategoryService.getActiveCategory();
		if(CollectionUtils.isEmpty(allCategory))
		{
			return ResponseEntity.noContent().build();
		}
		else 
		{
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getCategoryDetailsById(@PathVariable("id") Integer id) {
		
	try {
	    CategoryDto categoryDto = CategoryService.getCategoryById(id);
	    if (ObjectUtils.isEmpty(categoryDto)) 
	    {
	    	return new ResponseEntity<>("Category not found with Id = " + id, HttpStatus.NOT_FOUND);
	    }
         return new ResponseEntity<>(categoryDto, HttpStatus.OK);
	 }
	catch(ResourceNotFoundException e)
	{
		log.error("Controller :: getCategortDetailsById ::",e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	catch(Exception e)
	{
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> DeleteCategoryById(@PathVariable("id") Integer id) {
	    Boolean deleted = CategoryService.deleteCategory(id);
	    
	    if (deleted) 
	    {
	       return new ResponseEntity<>("Category deletd success", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("Category not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
