package com.datapp.chambre.model.devicestatus.device;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by kmluns on 27.06.2021
 */
@Accessors ( chain = true )
@Getter
@Setter
public class DeviceHeartBeat {
	Device device;

	boolean live;

	public DeviceHeartBeat ( Device device ) {
		this.device = device;
	}

	public DeviceHeartBeat ( Device device, boolean live ) {
		this( device );
		this.live = live;
	}

	@Override
	public String toString ()
	{
		return "DeviceHeartBeat{" + "device=" + device.getName() + ", live=" + live + '}';
	}
}
