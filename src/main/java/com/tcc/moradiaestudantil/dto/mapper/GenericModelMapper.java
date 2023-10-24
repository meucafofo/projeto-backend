package com.tcc.moradiaestudantil.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenericModelMapper {
	ModelMapper modelMappper = new ModelMapper();
	
	public <T> T convertEntity(Object dto, Class<T> cls) {
		return modelMappper.map(dto, cls);
	}
}
