<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedTrack - Admin</title>
<script src="./js/jquery.bootgrid.js"></script>
<script src="./js/jquery-2.2.0.min.js"></script>
<script src="./js/jquery.datetimepicker.full.js"></script>
<script src="./js/ezmodal.min.js"></script>



<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="./css/ezmodal.min.css"/>
<link rel="stylesheet" type="text/css" href="./css/jquery.datetimepicker.css"/>
<link rel="stylesheet" type="text/css" href="./css/jquery.bootgrid.css"/>

<script>

$(document).ready(function()
{
	$("#loadingdiv").hide();

	/*
	$(".menu2 a").append("<em></em>");
	$(".menu2 a").hover(function() 
	{
		$(this).find("em").animate({opacity: "show", top: "-75"}, "slow");
		var hoverText = $(this).attr("title");
	    $(this).find("em").text(hoverText);
	}, 
	function() 
	{
		$(this).find("em").animate({opacity: "hide", top: "-85"}, "fast");
	});
	*/

	String.prototype.replaceAll = function(str1, str2, ignore) 
	{
	    return this.replace(new RegExp(str1.replace(/([\/\,\!\\\^\$\{\}\[\]\(\)\.\*\+\?\|\<\>\-\&])/g,"\\$&"),(ignore?"gi":"g")),(typeof(str2)=="string")?str2.replace(/\$/g,""):str2);
	} 
	
	$('.menu2 a').click(function(event) 
	{
	   event.preventDefault();
       $("#confirmmodalcstmalertheader").html("Justification") ;
	   $('#confirmmodalmymodal').ezmodal('show');
	});
});

function setAllAppStatus()
{
	try
	{
		
		   $('#loadingdiv').fadeIn("slow");
		   $('body').css('cursor', 'wait');
		   var countofsel=$('#hiddenparam').val();

	   var selectedstatuses = [];
	   for(var ii=1; ii<=countofsel; ii++)
	   {
		   selectedstatuses.push($("#appstatusselectboxid"+ii+" option:selected").val());
	   }
	   var allcommetsare="undefinedval";
	   for(var ii=1; ii<=countofsel; ii++)
	   {
		   var commentsvalue=$("#appstatuscommentboxid"+ii+"").val();
		   commentsvalue=commentsvalue.replaceAll("&","");
		   commentsvalue=commentsvalue.replaceAll("^","");
		   if(commentsvalue=="" || commentsvalue==null || commentsvalue==undefined || commentsvalue.length<=0)
			   commentsvalue="No comments yet";
		   if(allcommetsare=="undefinedval")
			   allcommetsare=commentsvalue+"";
		   else
		   	   allcommetsare=allcommetsare+"^"+commentsvalue
	   }
		   var notificationmsg=document.getElementById("healthcheckmessageid").value
		   //alert("./dssservice?selectedstatuses="+selectedstatuses+"&ename=setallappsStatus&notificationmsg="+notificationmsg+"&allcommetsare="+allcommetsare+"");
		   
		  
		   $.ajax("./dssservice?selectedstatuses="+selectedstatuses+"&ename=setallappsStatus&notificationmsg="+notificationmsg+"&allcommetsare="+allcommetsare+"", 
		   {
		      success: function(data) 
		      {
		    	 $('#loadingdiv').fadeOut("slow");
		    	 $('body').css('cursor', 'auto');
		         $("#cstmalertheader").html("Success") ;
				 $("#cstmalertbody").css("color","green");
				 $("#cstmalertbody").html("Status has been updated successfully.") ;
				 $("#cstmalertbtn").html("OK") ;
				 $('#mymodal').ezmodal('show');
		      },
		      error: function() 
		      {
		    	   $('body').css('cursor', 'auto');
		    	   //alert("error");
		    	   $("#cstmalertheader").html("Failure") ;
				   $("#cstmalertbody").css("color","red");
				   $("#cstmalertbody").html("Error") ;
				   $("#cstmalertbtn").html("OK") ;
				   $('#mymodal').ezmodal('show');
		      }
		   });
	}
	catch(err)
	{

	}
}
</script>
<script >
    
    function editIncidentfun(incidentId){
		
 // alert(incidentId);
	  document.getElementById('incidentId').value=incidentId; 
	  document.getElementById('incidentFormBean').action = "/redtrackadmin/editIncident";
	  document.getElementById('incidentFormBean').submit(); 
		
	}
    </script>

<style type="text/css">
/*Fixed header and footer : START*/
html, body 
{
    height: 100%;
    width: 100%;
    margin: 0;
    padding: 0;
}
header
{
	//background: #000 none repeat-x scroll 0 0;
	background: #000 url("./resources/images/mixed.png") no-repeat scroll left center;
	//background-image: url("classpath:./images/dbs.png") repeat-x scroll 0 0;
    border-bottom: 9px solid #c00;
    height: 85px;
    
    width: 100%;
    //height: 90px;
    //background-color: #666666;
    position: fixed;
    top: 0;
}
#content 
{
    width: 90%;
    margin: 0 auto;
    padding: 110px 0;
}
footer 
{
    width: 100%;
    height: 40px;
    background-color: #000;
    position: fixed;
    bottom: 0;
}
/*Fixed header and footer : END*/


/*Onmouse over : START*/
.menu2 
{
	margin: 10px 0 0;
	padding: 0;
	list-style: none;
}
.menu2 li 
{
	padding: 0;
	margin: 0 0px;
	float: left;
	position: relative;
	text-align: center;
}
.menu2 a 
{
	padding: 7px 0px;
	display: block;
	color: #000000;
	width: 144px;
	text-decoration: none;
	font-weight: bold;
	background: url(images/button.gif) no-repeat center center;
}
.menu2 li em 
{
	font-weight: normal;
	background: url(images/hover.png) no-repeat;
	width: 180px;
	height: 45px;
	position: absolute;
	top: -85px;
	left: -15px;
	text-align: center;
	padding: 20px 12px 10px;
	font-style: normal;
	z-index: 2;
	display: none;
}
/*Onmouse over : END*/

/*Calender Start*/
.custom-date-style 
{
	background-color: red !important;
}
.input
{	

}
.input-wide
{
	width: 500px;
}
/*Calender End*/

.horizontaldivs
{
	height: 100%;
	float: left;
	padding-right: 30px;
	display:table-cell; 
	vertical-align:middle;
}
/*
#whatever div 
{
  display: inline;
  margin: 0 1em 0 1em;
  width: 30%;
}
*/

table, th, td {
    border: 1px solid #dddddd;
}

</style>
</head>

<body>

		<header>
			<p></p>
		</header>
		
		
		<div id="content">	
			
			 <nav>
			  <h3><a  href="newIncident">Create New  Incident</a></h3>
			  	 
			</nav> 
		 <h1>incident List</h1>
	        
	       	<form:form  name="incidentFormBean"  id="incidentFormBean" method="post" >
			<input type='hidden' id='incidentId' name='incidentId'/>
			<section id="main">
				<article>
					
					<!-- <div style="width: 790px;"> -->
					
						<table id="grid" class="table table-condensed table-hover table-striped" data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true" style="margin-top: 15px; width: 100%;">
						<!-- <table style="padding-top: 10px; padding-bottom: 10px;"> -->
						
						<thead style="background-color: #e9e7e7; color: #000000; font-weight: normal; font-size: 14px;">
                           
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT ID</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT TITLE</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT DESC</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT RESOLUTION</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT THREAT SEVERITY</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT CATEGORY</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">USEROTP ID</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT CONF_NUM</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT PARTICIPANT_CODE</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">USERDEVICE TOKENSTR</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">DATE CREATED</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">DATE UPDATED</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">UPDATED BY</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">STATUS</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">INCIDENT STATUS</th>
                                  <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">ACTION</th>
                          
	        	
				<c:forEach var="incident" items="${listofIncidents}">
	        	<tr>
	        	
					<td>${incident.incidentId}</td>
					<td>${incident.incidenttitle}</td>
					<td>${incident.incidentdesc}</td>
					
					<td>${incident.incidentresolution}</td>
					<td>${incident.incidentthreatseverity}</td>
					<td>${incident.incidentcategory}</td>
					<td>${incident.userotpid}</td>
					<td>${incident.incidentconfnum}</td>
					<td>${incident.incidentparticipantcode}</td>
					<td>${incident.userdevictokenstr}</td>
					<td>${incident.dateCreated}</td>
					
					<td>${incident.dateUpdated}</td>
					<td>${incident.updatedBy}</td>
					<td>${incident.status}</td>
					<td>${incident.incidentStatus}</td>
					
					<td>
					
						<a href="javascript:editIncidentfun('${incident.incidentId}');">Edit</a>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
				
				</article>
			</section>
	
			</form:form>
					
		</div>
		<footer>
			<p></p>
		</footer>

</body>
</html>