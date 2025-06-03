package com.demo.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class GenericResponse 
{
	private HttpStatus responseStatus;
	
	private String status;
	
	private String message;
	
	private Object data;
	
	public ResponseEntity<?> create()
	{
		Map<String, Object> m1= new LinkedHashMap<>();
		m1.put("status", status);
		m1.put("message", message);
		if(!ObjectUtils.isEmpty(data))
		{
			m1.put("data", data);
		}
		return new ResponseEntity<>(m1,responseStatus );
	}

}
