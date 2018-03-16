package com.dbs.redtrackadmin.request.dto;

import com.dbs.redtrackadmin.base.dto.AbstractBaseDTO;



public class DeviceTokenHelper  extends AbstractBaseDTO{
	

	private static final long serialVersionUID = 1L;
	
	private int devicePlatform;
	private String deviceTokenString;
	private String phoneNumber;	
 
	
	
	public String getDeviceTokenString() {
		return deviceTokenString;
	}
	public void setDeviceTokenString(String deviceTokenString) {
		this.deviceTokenString = deviceTokenString;
	}
	public int getDevicePlatform() {
		return devicePlatform;
	}
	public void setDevicePlatform(int devicePlatform) {
		this.devicePlatform = devicePlatform;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
