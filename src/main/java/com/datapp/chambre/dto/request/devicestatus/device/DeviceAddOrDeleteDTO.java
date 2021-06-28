package com.datapp.chambre.dto.request.devicestatus.device;

import com.datapp.chambre.dto.request.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by kmluns on 25.06.2021
 */
@Accessors (chain = true)
@Getter
@Setter
public class DeviceAddOrDeleteDTO extends BaseDTO {

	private String organizationID;

	private String name;

	private String ipAddress;

	private int port;

}
