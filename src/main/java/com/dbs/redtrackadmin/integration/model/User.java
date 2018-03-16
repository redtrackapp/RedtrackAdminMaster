package com.dbs.redtrackadmin.integration.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private long id;
	
	
	private String phoneNumber;
	
	
	private String fullName;
	 
	
	private String nickName;
	
	
	private int role;
	
	
	private String countryCode;	

	
	private String deviceTokenString;

	
	private String deviceID;
	
	
	private int devicePlatform;
	
	
	
	private Date dateCreated;
	
	
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
  
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

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



	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
//	
//	@OneToMany(mappedBy="customer",targetEntity=CallHistory.class,
//			   fetch=FetchType.EAGER)
//			   private Collection callHistory;  
}

/*
 CREATE TABLE `user` (
	`ID` INT(11) NOT NULL AUTO_INCREMENT,
	`PHONE_NUMBER` VARCHAR(50) NULL DEFAULT NULL,
	`FIRST_NAME` VARCHAR(50) NULL DEFAULT NULL,
	`LAST_NAME` VARCHAR(50) NULL DEFAULT NULL,
	`COUNTRY` VARCHAR(50) NULL DEFAULT NULL,
	`DATE_CREATED` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`ID`)
)
 * */
 