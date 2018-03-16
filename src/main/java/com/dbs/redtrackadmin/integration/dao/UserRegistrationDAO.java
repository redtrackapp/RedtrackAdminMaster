/**
 * 
 */
package com.dbs.redtrackadmin.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrackadmin.request.dto.DeviceTokenHelper;

/**
 * @author ADMINIBM
 *
 */
@Repository
public interface UserRegistrationDAO {
	
	public List<DeviceTokenHelper> getDeviceTokenStringList() ;

}
