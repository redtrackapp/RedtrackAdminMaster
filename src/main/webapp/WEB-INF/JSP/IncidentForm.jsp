<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedTrack - Admin</title>
<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="./resources/css/ezmodal.min.css"/>
<script src="./resources/js/jquery-2.2.0.min.js"></script>
<script src="./resources/js/ezmodal.min.js"></script>
<script type="text/javascript">
function showLayer()
{
   document.getElementById('savebuttonid').style.display='block';
   document.forms[0].submit();
}
</script>
<script>

var currentlyselectedValue;
var currentAppid;
var currentCntryid;
var currentBizunitid;
var currentmode;
$(document).ready(function()
{	
	/* currentmode='mapping';
	$("#loadingdiv").hide();
	$("#addnewappiddiv").hide();

	$("#updateappmappingid").addClass('selected');
    $("#addnewappid").removeClass('selected');
    
	$(".accordion h3:first").addClass("active");
	//$(".accordion table:not(:first)").hide();

	$(".accordion h3").click(function()
	{
		$(this).next("table").slideToggle("slow").siblings("table:visible").slideUp("slow");
		$(this).toggleClass("active");
		$(this).siblings("h3").removeClass("active");
	}); */

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
/* 	$("#addnewappid").click(function(event)
	{
		event.preventDefault();
		$('input:checkbox').attr('checked',false);
		$('#mapoldappiddiv').fadeOut('slow' ,function()
		{
			currentmode='addition';
		    $('#addnewappiddiv').fadeIn('slow');
		});

		$("#addnewappid").addClass('selected');
	    $("#updateappmappingid").removeClass('selected');
	});
 */
	/* $("#updateappmappingid").click(function(event)
	{
		event.preventDefault();
		$('input:checkbox').attr('checked',false);
		$('#addnewappiddiv').fadeOut('slow' ,function()
		{
			currentmode='mapping';
			$('#mapoldappiddiv').fadeIn('slow');
		});

		$("#updateappmappingid").addClass('selected');
	    $("#addnewappid").removeClass('selected');
	});
	 */
	/* $('#countryselectbox').change(function()
	{
	    $('input:checkbox').attr('checked',false);
	    var tempvar=$('#countryselectbox :selected').val();
	    if(!(tempvar=="" || tempvar==null || tempvar==undefined || tempvar.length<=0))
		{
	    	var temparr=tempvar.split("--");
		    try
		    {
		    	var countryarrids=temparr[1].split(",");
			    $.each(countryarrids, function( index, value ) 
			    {
			    	document.getElementById("cntry_"+value).checked=true;
			    });

			    var bizunitsids=temparr[2].split(",");
			    $.each(bizunitsids, function( index, value ) 
			    {
			    	document.getElementById("bizunit_"+value).checked=true;
			    });
			}
			catch(err)
			{

			}
		}
	});	 */
	
	$('.menu2 a').click(function(event) 
	{
	   event.preventDefault();
	   if('mapping'==currentmode)
	   {
		   var tempvar=$('#countryselectbox :selected').val();
		   var temparr=tempvar.split("--");
		   var selectedapplicationid=temparr[0];

		   if(selectedapplicationid=="" || selectedapplicationid==null || selectedapplicationid==undefined || selectedapplicationid.length<=0)
		   {
			   alert("Please select application.");
			   return;
		   }
		   
		   $('#loadingdiv').fadeIn("slow");
		   
		   var checkedcountries = [];
		   $('.countrycboxcss:checked').each(function() 
		   {
			   checkedcountries.push($(this).val());
		   });

		   var checkedbizunits = [];
		   $('.bizunitcboxcss:checked').each(function() 
		   {
			   checkedbizunits.push($(this).val());
		   });

		   if( (checkedcountries.length>0 && checkedbizunits.length<=0) || (checkedcountries.length<=0 && checkedbizunits.length>0) )
		   {
				if(checkedcountries.length==0)
				{
					alert("Please select countries.");
					return;
				}
				if(checkedbizunits.length==0)
				{
					alert("Please select business units.");
					return;
				}	
		   }
		   $('body').css('cursor', 'wait');

		   //alert("./dssservice?checkedcountries="+checkedcountries+"&checkedbizunits="+checkedbizunits+"&selectedapplicationid="+selectedapplicationid+"&ename=updateApplication");
		   //return;
		   $.ajax("./dssservice?checkedcountries="+checkedcountries+"&checkedbizunits="+checkedbizunits+"&selectedapplicationid="+selectedapplicationid+"&ename=updateApplication", 
		   {
		      success: function(data) 
		      {
		    	$('#loadingdiv').fadeOut("slow"); 
		    	$('body').css('cursor', 'auto');
		    	var ele=document.getElementById("countryselectbox");
		  	    for(var ii=0; ii<ele.length; ii++)
		  		{
		  	      if(ele.options[ii].value==tempvar) 
		  		  {
		  	        ele.options[ii].value=selectedapplicationid+"--"+checkedcountries+"--"+checkedbizunits;
		  	      }
		  		}
		        //alert("Application has been mapped, successfully.");
		        $("#cstmalertheader").html("Success") ;
			    $("#cstmalertbody").css("color","green");
			    $("#cstmalertbody").html("Application has been mapped, successfully.") ;
			    $("#cstmalertbtn").html("OK") ;
			    $('#mymodal').ezmodal('show');
		      },
		      error: function() 
		      {
		    	  //alert("error");
		    	  $("#cstmalertheader").html("Failure") ;
			      $("#cstmalertbody").css("color","red");
			      $("#cstmalertbody").html("Error") ;
			      $("#cstmalertbtn").html("OK") ;
			      $('#mymodal').ezmodal('show');
		      }
		   });
	   }
	   else if('addition'==currentmode)
	   {
		   
	    
	   
	  
	});
	
});


</script>


<style type="text/css">
a
{
    color: blue;   
}
.selected 
{
     color: red;   
}

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

a.disabled { color:gray; }

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
	background: url(./resources/images/button.gif) no-repeat center center;
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

.accordion 
{
	width: 800px;
	border-bottom: solid 1px #c4c4c4;
}
.accordion h3 
{
	background: #e9e7e7 right -51px;
	padding: 7px 15px;
	margin: 0;
	
	font-weight: bold;
	font-family: "Arial";
	font-style: normal;
	font-size: 13px;
	
	border: solid 1px #c4c4c4;
	border-bottom: none;
	cursor: pointer;
}
.accordion h3:hover 
{
	background-color: #e3e2e2;
}
.accordion h3.active 
{
	background-position: right 5px;
}
.accordion table
{
	background: #f7f7f7;
	margin: 0;
	padding: 10px 15px 20px;
	border-left: solid 1px #c4c4c4;
	border-right: solid 1px #c4c4c4;
}

.accordion table label
{
	font-family: "Arial";
	font-style: normal;
	font-size: 12px;
}
</style>
</head>

<body>

		<header>
			<p></p>
		</header>
		
		
		<div id="content">	
			
			<nav>
			 <a  href="/redtrackadmin/">Back</a>
			  	 
			</nav> 
			<h1>New/Edit Incident</h1>
		<form:form  method="post" action="/redtrackadmin/saveIncident" modelAttribute="incident">
			<section id="main">
				<article>
						<table id="grid" class="table table-condensed table-hover table-striped" data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true" style="margin-top: 15px; width: 100%;">
					<tr>
				<td>INCIDENT_ID:</td>
				<td><form:input path="incidentId" required="required" /></td>
			</tr>
			<tr>
				<td>INCIDENT_TITLE:</td>
				<td><form:input path="incidenttitle" required="required" /></td>
			</tr>
			<tr>
				<td>INCIDENT_DESC:</td>
				<td><form:input path="incidentdesc" required="required"/></td>
			</tr>
			<tr>
				<td>INCIDENT_RESOLUTION:</td>
				<td><form:input path="incidentresolution" required="required"/></td>
			</tr>
			<tr>
				<td>INCIDENT_THREAT_SEVERITY:</td>
				<td><form:input path="incidentthreatseverity" required="required"/></td>
			</tr>
			<tr>
				<td>INCIDENT_CATEGORY:</td>
				<td><form:input path="incidentcategory" required="required"/></td>
			</tr>
			<tr>
				<td>USEROTP_ID:</td>
				<td><form:input path="userotpid" required="required"/></td>
			</tr>
			<tr>
				<td>INCIDENT_CONF_NUM:</td>
				<td><form:input path="incidentconfnum" required="required"/></td>
			</tr>
			<tr>
				<td>INCIDENT_PARTICIPANT_CODE:</td>
				<td><form:input path="incidentparticipantcode" required="required"/></td>
			</tr>
			<tr>
				<td>USERDEVICE_TOKENSTR:</td>
				<td><form:input path="userdevictokenstr" required="required"/></td>
			</tr>
			<tr>
				<td>DATE_CREATED:</td>
				<td><form:input path="dateCreated" required="required"/></td>
			</tr>
			<tr>
				<td>DATE_UPDATED:</td>
				<td><form:input path="dateUpdated" required="required"/></td>
			</tr>
			<tr>
				<td>UPDATED_BY:</td>
				<td><form:input path="updatedBy" required="required"/></td>
			</tr>
			<tr>
				<td>STATUS:</td>
				<td><form:input path="status" required="required"/></td>
			</tr>
			<tr>
				<td>INCIDENT_STATUS:</td>
				<td><form:input path="incidentStatus" required="required"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="submitApp" id="submitbtnid" title="Adds the application to the System." value="Save" onclick="showLayer();return false;">
				<div id="savebuttonid" style="position:absolute;display:none">
						<table >
							<tr>
								<td  align="center"><img src="./resources/images/processbar.gif"/></td>
							</tr>
							<tr>
								<td  align="center"><span style="color: red; padding-left: 6px;">Saving please wait ...</span></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
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