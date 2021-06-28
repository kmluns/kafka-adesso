package com.datapp.chambre.service;

import com.datapp.chambre.ChambreApplication;
import com.datapp.chambre.authorization.dto.AccountDTO;
import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.authorization.model.AccountDetail;
import com.datapp.chambre.authorization.model.Role;
import com.datapp.chambre.dto.request.devicestatus.device.DeviceAddOrDeleteDTO;
import com.datapp.chambre.model.devicestatus.device.Device;
import com.datapp.chambre.repository.authorization.AccountRepository;
import com.datapp.chambre.repository.devicestatus.device.DeviceRepository;
import com.datapp.chambre.service.common.user.AccountService;
import com.datapp.chambre.service.devicestatus.device.DeviceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Scanner;

/**
 * created by kmluns
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChambreApplication.class)
public class DeviceHearthKafkaTest
{
    DeviceService deviceService;

    @Autowired
    public void setDeviceService(DeviceService deviceService){ this.deviceService = deviceService;}

    @Before
    public void init() {
        DeviceAddOrDeleteDTO deviceDTO = new DeviceAddOrDeleteDTO();
        deviceDTO.setName( "device1" ).setIpAddress( "http://localhost" ).setPort( 52645 );

        DeviceAddOrDeleteDTO deviceDTO2 = new DeviceAddOrDeleteDTO();
        deviceDTO2.setName( "device2" ).setIpAddress( "http://localhost" ).setPort( 8080 );

    }

    @Test
    public void createAccount() {
        Scanner sc = new Scanner( System.in );
        sc.nextLine();
    }

}
