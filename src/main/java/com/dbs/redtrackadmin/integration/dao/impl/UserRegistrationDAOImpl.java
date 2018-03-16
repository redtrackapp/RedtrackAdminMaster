/**
 * 
 */
package com.dbs.redtrackadmin.integration.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.dbs.redtrackadmin.integration.dao.UserRegistrationDAO;
import com.dbs.redtrackadmin.request.dto.DeviceTokenHelper;


/**
 * @author ADMINIBM
 *
 */
public class UserRegistrationDAOImpl implements UserRegistrationDAO {
	private static final Logger logger = Logger.getLogger(UserRegistrationDAOImpl.class);
	
	 private JdbcTemplate jdbcTemplate;
	    // JdbcTemplate setter
	    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	    {
	        this.jdbcTemplate = jdbcTemplate;
	    }

	@Override
	@SuppressWarnings({ "unchecked", "unused" })
	public List<DeviceTokenHelper> getDeviceTokenStringList() {
		
		logger.info("Inside UserRegistrationDAOImpl :: getDeviceTokenStringList() :: Start" );
		 
		String sql = "select DEVICE_TOKEN_STRING,DEVICE_PLATFORM,PHONE_NUMBER FROM user where STATUS = 'A'";
		List<DeviceTokenHelper> tokenList = jdbcTemplate.query(sql, new ResultSetExtractor<List<DeviceTokenHelper>>()
        {
            @Override
            public List<DeviceTokenHelper> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<DeviceTokenHelper> list = new ArrayList<DeviceTokenHelper>();
                while (rs.next())
                {
                	DeviceTokenHelper deviceTokenHelper = new DeviceTokenHelper();
                	deviceTokenHelper.setDeviceTokenString(rs.getString(1));
                	deviceTokenHelper.setDevicePlatform(rs.getInt(2));
                	deviceTokenHelper.setPhoneNumber(rs.getString(3));
                    list.add(deviceTokenHelper);
                }
                logger.info(" List Size ::" + list.size());
                return list;
            }

        });
		logger.info("Inside UserRegistrationDAOImpl :: getDeviceTokenStringList() :: tokenList Size "  + tokenList.size());
		return tokenList;	 
	}
}
