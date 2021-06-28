package com.datapp.chambre.utils.devicestatus;

import com.datapp.chambre.model.devicestatus.device.Device;
import com.datapp.chambre.model.devicestatus.device.DeviceHeartBeat;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by kmluns on 27.06.2021
 */
public class DeviceNetworkUtil {

	private DeviceNetworkUtil(){}

	public static Future<DeviceHeartBeat> deviceIsOpen ( final ExecutorService es, Device device ) {
		return es.submit( () -> {
			try {
				Socket socket = new Socket();
				socket.connect( new InetSocketAddress( device.getIpAddress(), device.getPort() ), 3000 );
				socket.close();
				return new DeviceHeartBeat(device, true);
			}
			catch ( Exception ex ) {
				return new DeviceHeartBeat(device, false);
			}
		} );
	}

}
