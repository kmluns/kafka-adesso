package com.datapp.chambre.service.devicestatus.device.impl;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.dto.request.devicestatus.device.DeviceAddOrDeleteDTO;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.exception.runtime.devicestatus.DeviceNotFoundException;
import com.datapp.chambre.model.devicestatus.device.Device;
import com.datapp.chambre.model.devicestatus.organization.Organization;
import com.datapp.chambre.model.devicestatus.organization.authorize.OrganizationAuthorizationOperation;
import com.datapp.chambre.repository.devicestatus.device.DeviceRepository;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.service.devicestatus.device.DeviceService;
import com.datapp.chambre.service.devicestatus.organization.OrganizationService;
import com.datapp.chambre.utils.devicestatus.organization.authorization.OrganizationAuthorizeUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by kmluns on 25.06.2021
 */
@Service
public class DeviceServiceImpl implements DeviceService {

	DeviceRepository deviceRepository;

	OrganizationService organizationService;

	GenericResponseService genericResponseService;

	ModelMapper modelMapper;

	@Autowired
	public void setDeviceRepository(DeviceRepository deviceRepository){
		this.deviceRepository = deviceRepository;
	}

	@Autowired
	public void setGenericResponseService(GenericResponseService genericResponseService){ this.genericResponseService = genericResponseService;}

	@Autowired
	public void setModelMapper(ModelMapper modelMapper){ this.modelMapper = modelMapper;}

	@Autowired
	public void setOrganizationService(OrganizationService organizationService){ this.organizationService = organizationService;}


	@PostConstruct
	public void createTwoDevice(){
//		DeviceAddOrDeleteDTO deviceDTO = new DeviceAddOrDeleteDTO();
//		deviceDTO.setName( "device1" ).setIpAddress( "http://localhost" ).setPort( 52645 );
//
//		DeviceAddOrDeleteDTO deviceDTO2 = new DeviceAddOrDeleteDTO();
//		deviceDTO2.setName( "device2" ).setIpAddress( "http://localhost" ).setPort( 8080 );

		deviceRepository.deleteAll();

		Device device1 = new Device();
		device1.setName( "device1" ).setIpAddress( "0.0.0.0" ).setPort( 52645 );

		Device device2 = new Device();
		device2.setName( "device2" ).setIpAddress( "0.0.0.0" ).setPort( 3000 );

		deviceRepository.save( device1 );
		deviceRepository.save( device2 );


	}

	public GenericResponse<Device> addDevice( Account currentAccount, DeviceAddOrDeleteDTO deviceAddOrDeleteDTO) {
		Device device = null;
		if (existDevice( deviceAddOrDeleteDTO.getIpAddress(), deviceAddOrDeleteDTO.getPort())) {
			device = getDevice( deviceAddOrDeleteDTO.getIpAddress(), deviceAddOrDeleteDTO.getPort() );
		} else {
			Organization organization = organizationService.getOrganization(deviceAddOrDeleteDTO.getOrganizationID());

			if( OrganizationAuthorizeUtil.authorize( OrganizationAuthorizationOperation.ADD_MODEM, organization, currentAccount  ) ){
				device = modelMapper.map(deviceAddOrDeleteDTO, Device.class);
				device.setId( UUID.randomUUID().toString());
				device = deviceRepository.save( device );

				organizationService.addDevice(currentAccount, organization, device);
				device.addOrganization( organization );
				device = deviceRepository.save( device );
			}
			
		}
		return genericResponseService.createResponseNoError(device);
	}

	public Device getDevice(String deviceId) {
		Optional<Device> optionalDevice = deviceRepository.findById( deviceId );
		return optionalDevice.orElseThrow( DeviceNotFoundException::new);
	}

	public Device getDevice(String ipAddress, int port) {
		Optional<Device> optionalDevice = deviceRepository.findByIpAddressAndPort( ipAddress,port);
		return optionalDevice.orElseThrow( DeviceNotFoundException::new);
	}

	public boolean existDevice(String ipAddress, int port) {
		Optional<Device> optionalDevice = deviceRepository.findByIpAddressAndPort( ipAddress,port);
		return optionalDevice.isPresent();
	}

	@Override
	public List<Device> getDeviceList ()
	{
		return deviceRepository.findAll();
	}

}
