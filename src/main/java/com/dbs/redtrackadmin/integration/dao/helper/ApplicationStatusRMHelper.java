package com.dbs.redtrackadmin.integration.dao.helper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dbs.redtrackadmin.request.dto.ApplicationStatusDTO; 

public class ApplicationStatusRMHelper implements RowMapper<ApplicationStatusDTO> {  
	
	public ApplicationStatusDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	 
		ApplicationStatusDTO applicationStatusDTO = new ApplicationStatusDTO();
		applicationStatusDTO.setCountryCode(rs.getString("country_code"));
		applicationStatusDTO.setUnitId(rs.getString("unit_id"));
		applicationStatusDTO.setAppId(rs.getString("app_id"));
		applicationStatusDTO.setStatus(rs.getString("status"));		 
  		return applicationStatusDTO;  
	}  
}  