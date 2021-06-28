package com.datapp.chambre.dto.request.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by kmluns on 25.06.2021
 */
@Accessors(chain = true)
@Getter
@Setter
public class BaseDTO
{
	private String id;

	public BaseDTO(){}
	public BaseDTO(String id){
		this.id = id;
	}
}
