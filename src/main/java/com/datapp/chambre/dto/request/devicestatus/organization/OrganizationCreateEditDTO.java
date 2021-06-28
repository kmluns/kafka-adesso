package com.datapp.chambre.dto.request.devicestatus.organization;

import com.datapp.chambre.dto.request.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by kmluns on 25.06.2021
 */
@Accessors(chain = true)
@Getter
@Setter
public class OrganizationCreateEditDTO extends BaseDTO {

	private String name;

	public OrganizationCreateEditDTO()
	{
	}

	public OrganizationCreateEditDTO(String name){
		super();
		this.name = name;
	}

}
