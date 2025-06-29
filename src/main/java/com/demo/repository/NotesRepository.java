package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer> 
{
	

}
