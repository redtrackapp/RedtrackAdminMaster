package com.dbs.redtrackadmin.integration.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author Imran
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrackadmin.integration.dao.AdminViewDAO;
import com.dbs.redtrackadmin.integration.dao.helper.ApplicationStatusRMHelper;
import com.dbs.redtrackadmin.request.dto.ApplicationStatusDTO;
import com.dbs.redtrackadmin.request.dto.ApplicationsDTO;
import com.dbs.redtrackadmin.request.dto.BusinessUnitsDTO;
import com.dbs.redtrackadmin.request.dto.CountryMasterDTO;
 
@Repository
public class AdminViewDAOImpl implements AdminViewDAO {

	private static final Logger logger = Logger.getLogger(AdminViewDAOImpl.class);
	
    private JdbcTemplate jdbcTemplate;
    // JdbcTemplate setter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    

    //for testing only
    @Override
    public Map<String, String> getlistofApps() {
    	Map<String, String> listofapptypes = new HashMap<String, String>();
    	listofapptypes.put("appid1", "App Desc");
    	listofapptypes.put("appid2", "App Desc2");
    	listofapptypes.put("appid3", "App Desc3");
    	listofapptypes.put("appid4", "App Desc4");
    	listofapptypes.put("appid5", "App Desc5");
    	listofapptypes.put("appid6", "App Desc6");

    	return listofapptypes;
    }
     
    
	@Transactional
	@Override
    public String findandRemoveApplicationStatusById(String strAppID) {
		logger.info("Inside AdminViewDAOImpl :: findApplicationStatusById() :: start");
 
        String sql = "delete from application_status where app_id=?";
        jdbcTemplate.update(sql, new Object[] { strAppID });
		return  null;
	}
	  
 
	// will have a problem when zero rows retreived. need to change to be like the findApplicationStatusByParam method with try/catch
	@Override
	@Transactional
    public List<ApplicationsDTO> getApplicationList()
    {
        String sql = "select * from Applications";
        sql = "select * from applications order by application_name asc";

        List<ApplicationsDTO> applicationList = jdbcTemplate.query(sql, new ResultSetExtractor<List<ApplicationsDTO>>()
        {
            @Override
            public List<ApplicationsDTO> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<ApplicationsDTO> list = new ArrayList<ApplicationsDTO>();
                while (rs.next())
                {
                	ApplicationsDTO applicationsDTO = new ApplicationsDTO();
                	applicationsDTO.setAppId(rs.getString(1));
                	applicationsDTO.setApplicationName(rs.getString(2));
                	//skipped country code
                	applicationsDTO.setAppCode(rs.getString(4));
                	applicationsDTO.setAppActive(rs.getString(5));
                    list.add(applicationsDTO);
                }
                return list;
            }

        });
        return applicationList;
    }
	
	// will have a problem when zero rows retreived. need to change to be like the findApplicationStatusByParam method with try/catch
	@Override
	@Transactional
	public List<ApplicationStatusDTO> getApplicationStatusList() { 
        String sql = "select  a.country_code, a.unit_Id,a.app_id , a.STATUS, a.APP_COMMENTS , b.APPLICATION_NAME "
        		+ "from application_status a left join applications b "
        		+ "on a.app_id = b.app_id "
        		+ "Where a.status = (select MIN(status) from application_status where app_id = a.APP_ID ) "
        		+ "group by a.app_id order by b.application_name";
        		

        List<ApplicationStatusDTO> applicationStatusList = jdbcTemplate.query(sql, new ResultSetExtractor<List<ApplicationStatusDTO>>()
        {
            @Override
            public List<ApplicationStatusDTO> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<ApplicationStatusDTO> list = new ArrayList<ApplicationStatusDTO>();
                System.out.println("result list in DAO ******START************* ");
                while (rs.next())
                {
                	ApplicationStatusDTO applicationStatusDTO = new ApplicationStatusDTO();                	
                	applicationStatusDTO.setCountryCode(""+rs.getString(1));
                	applicationStatusDTO.setUnitId(""+rs.getString(2));
                	applicationStatusDTO.setAppId(""+rs.getString(3));
                	applicationStatusDTO.setStatus(""+rs.getString(4));
                	applicationStatusDTO.setAppComments(""+rs.getString(5));
                	applicationStatusDTO.setApplicationName(""+rs.getString(6));
                    list.add(applicationStatusDTO);
//                    System.out.println("Status: " +applicationStatusDTO.getStatus());
                }
                return list;
            }

        });
        System.out.println("result list in DAO ***END**************** "+applicationStatusList.size());
        System.out.println("result list is**************** "+applicationStatusList);
        return applicationStatusList;
    }
           
	
	// will have a problem when zero rows retreived. need to change to be like the findApplicationStatusByParam method with try/catch
	@Override
	@Transactional
    public List<ApplicationStatusDTO> getApplicationStatusListById(String strAppID ){
        String sql = "SELECT * FROM application_status where app_id=?";
       
       List<ApplicationStatusDTO> applicationStatusList= new ArrayList<ApplicationStatusDTO>();
       
       List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] { strAppID });
       for (Map row : rows) {
    	ApplicationStatusDTO applicationStatusDTO = new ApplicationStatusDTO();
    	
    	applicationStatusDTO.setCountryCode((String)row.get("country_code"));
    	applicationStatusDTO.setUnitId((String)row.get("unit_id"));
    	applicationStatusDTO.setAppId((String)row.get("app_id"));
    	applicationStatusDTO.setStatus((String)row.get("status"));
    	 
        applicationStatusList.add(applicationStatusDTO);
       }
        return applicationStatusList;
      }
  
	    	  
	
	// will have a problem when zero rows retreived. need to change to be like the findApplicationStatusByParam method with try/catch
	@Override
	@Transactional
	public List<BusinessUnitsDTO> getBusinessUnitList() { 
        String sql = "select * from business_units";
        sql = "select * from business_units order by description";
        List<BusinessUnitsDTO> applicationStatusList = jdbcTemplate.query(sql, new ResultSetExtractor<List<BusinessUnitsDTO>>()
        {
            @Override
            public List<BusinessUnitsDTO> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<BusinessUnitsDTO> list = new ArrayList<BusinessUnitsDTO>();
                while (rs.next())
                {
                	BusinessUnitsDTO businessUnitsDTO = new BusinessUnitsDTO();
                	businessUnitsDTO.setUnitId(rs.getString(1));
                	businessUnitsDTO.setDescription(rs.getString(2));
                    list.add(businessUnitsDTO);
                }
                return list;
            }

        });
        return applicationStatusList;
    }
           
 
  
// will have a problem when zero rows retreived. need to change to be like the findApplicationStatusByParam method with try/catch  
	@Override
	@Transactional
	public List<CountryMasterDTO> getCountryList() { 
        String sql = "select * from country_master";
        sql = "select * from country_master order by country_description";
        List<CountryMasterDTO> countryList = jdbcTemplate.query(sql, new ResultSetExtractor<List<CountryMasterDTO>>()
        {
            @Override
            public List<CountryMasterDTO> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<CountryMasterDTO> list = new ArrayList<CountryMasterDTO>();
                while (rs.next())
                {
                	CountryMasterDTO countryMasterDTO = new CountryMasterDTO();
                	countryMasterDTO.setCountryCode(rs.getString(1));
                	countryMasterDTO.setCountryDescription(rs.getString(2));
                    list.add(countryMasterDTO);
                }
                return list;
            }

        });
        return countryList;
    } 

	    
    
    public ApplicationStatusDTO findApplicationStatusByParam(String appid,String country, String businessUnit) {
    	logger.info("inside findApplicationStatusByParam :: start" );
    	logger.info("appid: "+ appid + "country: "+ country +" businessUnit: "+businessUnit);
    	    	
    	ApplicationStatusDTO applicationStatusDTO =  null;
    	
    	try {
    		String sql = "select * from application_status where app_id=? and country_code=? and unit_id=?";
    		applicationStatusDTO = (ApplicationStatusDTO) jdbcTemplate.queryForObject(sql, new Object[]{ appid, country, businessUnit }, new ApplicationStatusRMHelper());
    	} catch (EmptyResultDataAccessException  ex) {
    		logger.info("no record found: " + ex.getMessage());
    	} catch (Exception  ex) {
    		logger.info("Exception: " + ex.getMessage());
    	}    	

    	if (null!=applicationStatusDTO){
    		logger.info("applicationStatusDTO: "+ applicationStatusDTO);
    	}
    	
        return applicationStatusDTO;
     }
    
    
    
	@Override
	@Transactional
	public String submitapplicationFormBean(String appid, String country, String businessUnit) {
		logger.info("Inside AdminViewDAOImpl :: submitapplicationFormBean() :: start");	
		logger.info("submitapplicationFormBean:  appid: "+ appid + "country: "+ country +" businessUnit: "+businessUnit+"<<<<<<<<<<<<<<");	
		try {
		ApplicationStatusDTO applicationStatus = findApplicationStatusByParam( appid,  country,  businessUnit);
		if (applicationStatus==null) {
	        String sql = "insert into application_status(country_code, unit_id, app_id) values(?,?,?)";
	        jdbcTemplate.update(sql, new Object[]{country, businessUnit, appid});
			logger.info("Inside AdminViewDAOImpl :: submitapplicationFormBean() :: successfully saved");
			return "record Saved";
		} else { 
			logger.info("Inside AdminViewDAOImpl :: submitapplicationFormBean() :: end");	
			return "record not Saved";
		} 
		} catch (Exception ex ){
			logger.info("Exception here:" +ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	 
	
	
	  public ApplicationsDTO findApplicationById(String appid) {
	    	String sql = "select * from applications  where app_id=?";
	        ApplicationsDTO applicationsDTO = (ApplicationsDTO) jdbcTemplate.queryForObject(sql, new Object[] { appid }, new RowMapper<ApplicationsDTO>()
	        {
	            @Override
	            public ApplicationsDTO mapRow(ResultSet rs, int rowNum) throws SQLException 
	            {
	               	ApplicationsDTO applicationsDTO = new ApplicationsDTO();
	            	applicationsDTO.setAppId(rs.getString(1));
	            	applicationsDTO.setApplicationName(rs.getString(2));
	            	//skipped country code 
	            	applicationsDTO.setAppCode(rs.getString(4));
	            	applicationsDTO.setAppActive(rs.getString(5));
	                return applicationsDTO;
	            }
	        });
	        return applicationsDTO;
	    }
	    
	  
	@Override
	@Transactional
	public String addNewapplicationForm(String appId, String applicationName, String appCode, String appActive) {
		
		ApplicationsDTO applications =findApplicationById(appId); 
		if (applications==null) {
	        String sql = "insert into applications (app_id, applicattion_name, app_code, app_active) values(?,?,?,?)";
	        System.out.println("dao called");
	        jdbcTemplate.update(sql, new Object[]{ appId, applicationName, appCode, appActive});

			logger.info("Inside AdminViewDAOImpl :: addNewapplicationForm() :: addNewapplicationForm saved");
			return "record Saved";
		} else { 
			logger.info("Inside AdminViewDAOImpl :: addNewapplicationForm() :: end");	
			return "record not Saved";
		}
		 
	}


	@Override
	@Transactional
	public String saveApplicaitonStatusForm(String appid, String country, String businessUnit, String status,String appComments)  {
  
        String sql = "update application_status set status =?,app_comments=? where app_id=? ";
        int results = jdbcTemplate.update(sql, new Object[] { status, appComments, appid }); 			 
		 
        if (results!=0) {
			logger.info("Inside AdminViewDAOImpl :: saveApplicaitonStatusForm() :: saveApplicaitonStatusForm saved");
			return "record Saved";
		} else {
			
			logger.info("Inside AdminViewDAOImpl :: saveApplicaitonStatusForm() :: end");	
			return "record not Saved";
		}
	}
	
	 
}
