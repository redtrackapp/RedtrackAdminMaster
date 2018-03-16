<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	background: #000 url("./images/mixed.png") no-repeat scroll left center;
	//background-image: url("./images/dbs.png") repeat-x scroll 0 0;
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
			  	<a href="./index.jsp" style="font-weight: bold; font-size: 13px;">Back to home</a> 
			  	<!-- <a href="./dssservice?ename=viewhealthCheck" style="font-weight: bold; font-size: 13px;">View current health check</a> | 
			  	<a href="./dssservice?ename=getAllMQTTAcks" style="font-weight: bold; font-size: 13px;">MQTT Acknowledgements</a> --> 
			</nav> 
		
			<form name="myform" id="myform">
			<section id="main">
				<article>
					
					<div style="width: 790px;">
					
						<table id="grid" class="table table-condensed table-hover table-striped" data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true" style="margin-top: 15px; width: 100%;">
						<!-- <table style="padding-top: 10px; padding-bottom: 10px;"> -->
						
						<thead style="background-color: #e9e7e7; color: #000000; font-weight: normal; font-size: 14px;">
                            <tr>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">Application name</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">Application status</th>
                                <th data-column-id="textappnameid" data-align="center" data-header-align="center" style="data-css-class="cell" data-header-css-class="column">Application comments</th>
                            </tr>
                        </thead>
						<tbody style="font-weight: normal; font-size: 13px;">
						<c:set var="i" scope="page" value="0"/>
						<c:set var="j" scope="page" value="2"/>
						<c:forEach items="${appstatusarrayobj}" var="appstatusarrayobj">
						<c:set var="i" value="${i + 1}" scope="page"/>
						
							<tr>
						        <td>
						        	<label style="font-family: Arial; font-style: normal; font-weight: normal;">${appstatusarrayobj.apptype_name}</label>
						        </td>
								<td>
						        	<select name="appstatusselectboxid${i}" id="appstatusselectboxid${i}">
										<option value="" selected="selected">Please select application</option>
										<c:choose>
										<c:when test="${appstatusarrayobj.apptype_type_status == 1}">
											<option value="${appstatusarrayobj.apptype_name_id}--1" selected='selected'>Red</option>
										</c:when>
										<c:otherwise>
											<option value="${appstatusarrayobj.apptype_name_id}--1">Red</option>
										</c:otherwise>
										</c:choose>
										
										<c:choose>
										<c:when test="${appstatusarrayobj.apptype_type_status == 2}">
											<option value="${appstatusarrayobj.apptype_name_id}--2" selected='selected'>Yellow</option>
										</c:when>
										<c:otherwise>
											<option value="${appstatusarrayobj.apptype_name_id}--2">Yellow</option>
										</c:otherwise>
										</c:choose>
										
										<c:choose>
										<c:when test="${appstatusarrayobj.apptype_type_status == 3}">
											<option value="${appstatusarrayobj.apptype_name_id}--3" selected='selected'>Green</option>
										</c:when>
										<c:otherwise>
											<option value="${appstatusarrayobj.apptype_name_id}--3">Green</option>
										</c:otherwise>
										</c:choose>
									</select>
						        </td>
						        
						        <td>
						        	<!-- <textarea rows="3" cols="3"></textarea> -->
						        	<input style="width: 350px;" type="text" id="appstatuscommentboxid${i}" name="appstatuscommentboxid${i}" placeholder="Enter your comments" value="${appstatusarrayobj.apptype_type_long_desc}"/>
						        </td>
						    </tr>   
							
						
						
						<!-- 
						<tr style="text-align: center; vertical-align: middle;">
						    <td style="text-align: center; vertical-align: middle; width: 100%; height: 100%;">
						       	<div class="horizontaldivs">
						       		<label>${appstatusarrayobj.apptype_name}</label>
						       	</div>
						
						        <div class="horizontaldivs">
						        	<select name="appstatusselectboxid${i}" id="appstatusselectboxid${i}">
										<option value="" selected="selected">Please select application</option>
										<c:choose>
										<c:when test="${appstatusarrayobj.apptype_type_status == 1}">
											<option value="${appstatusarrayobj.apptype_name_id}--1" selected='selected'>Red</option>
										</c:when>
										<c:otherwise>
											<option value="${appstatusarrayobj.apptype_name_id}--1">Red</option>
										</c:otherwise>
										</c:choose>
										
										<c:choose>
										<c:when test="${appstatusarrayobj.apptype_type_status == 2}">
											<option value="${appstatusarrayobj.apptype_name_id}--2" selected='selected'>Yellow</option>
										</c:when>
										<c:otherwise>
											<option value="${appstatusarrayobj.apptype_name_id}--2">Yellow</option>
										</c:otherwise>
										</c:choose>
										
										<c:choose>
										<c:when test="${appstatusarrayobj.apptype_type_status == 3}">
											<option value="${appstatusarrayobj.apptype_name_id}--3" selected='selected'>Green</option>
										</c:when>
										<c:otherwise>
											<option value="${appstatusarrayobj.apptype_name_id}--3">Green</option>
										</c:otherwise>
										</c:choose>
									</select>
						        </div>
						        
						        <div class="horizontaldivs">
							        <input type="text" disabled="disabled" placeholder="Comments, is disabled" value="${appstatusarrayobj.apptype_type_long_desc}"/>
						        </div>
						    </td>
						</tr>
						-->
						</c:forEach>
						</tbody>
						<input type="hidden" name='hiddenparam' id='hiddenparam' value="${i}"/>
						</table>
						
					
					</div>		
					
					<ul class="menu2">
						<li>
							<a href="#" id="submitbtnid" title="It updates the status.">Save</a>		
						</li>
					</ul>
					<div id="loadingdiv">
						<table>
							<tr>
								<td><img src="./images/processbar.gif"/></td>
							</tr>
							<tr>
								<td><span style="color: red; padding-left: 6px;">Saving please wait ...</span></td>
							</tr>
						</table>
					</div>
					
				</article>
			</section>
			
			
			<div id="mymodal" class="ezmodal">
                 <div class="ezmodal-container">
                     <div class="ezmodal-header" style="background-color: #e9e7e7;">
                         <div class="ezmodal-close" data-dismiss="ezmodal">x</div>
                         <span id="cstmalertheader">Success</span>
                     </div>
                     <div class="ezmodal-content">
                         <p style="color: green;" id="cstmalertbody">Status has been updated successfully.</p>
                     </div>
                     <div class="ezmodal-footer">
                         <button type="button" class="btn" data-dismiss="ezmodal" id="cstmalertbtn">OK</button>
                     </div>
                 </div>
             </div>	
             
             
             <div id="confirmmodalmymodal" class="ezmodal">
                 <div class="ezmodal-container">
                     <div class="ezmodal-header" style="background-color: #e9e7e7;">
                         <div class="ezmodal-close" data-dismiss="ezmodal">x</div>
                         <span id="confirmmodalcstmalertheader">Justification</span>
                     </div>
                     <div class="ezmodal-content" style="padding-top: 30px; padding-bottom: 30px;">
                        <input type="text" id="healthcheckmessageid" value="CBG Health Check - All applications are fully operational." style="width: 100%;" placeholder="CBG Health Check - All applications are fully operational.">
                     </div>
                     <div class="ezmodal-footer">
                         <button type="button" class="btn" data-dismiss="ezmodal" id="confirmmodalcstmalertbtn" onclick="setAllAppStatus()">OK</button>
                     </div>
                 </div>
             </div>
             		
			
			
			</form>
					
		</div>
		<footer>
			<p></p>
		</footer>

<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() 
{
	
});

//Calender component
$.datetimepicker.setLocale('en');

$('#datetimepicker_format').datetimepicker({value:'2015/04/15 05:03', format: $("#datetimepicker_format_value").val()});
$("#datetimepicker_format_change").on("click", function(e)
{
	$("#datetimepicker_format").data('xdsoft_datetimepicker').setOptions({format: $("#datetimepicker_format_value").val()});
});

$("#datetimepicker_format_locale").on("change", function(e)
{
	$.datetimepicker.setLocale($(e.currentTarget).val());
});

$('#datetimepicker').datetimepicker(
{
	dayOfWeekStart : 1,
	lang:'en',
	disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
	startDate:	'2016/01/01'
});
//$('#datetimepicker').datetimepicker({value:'2015/04/15 05:03',step:10});
$('.some_class').datetimepicker();
</script>
</body>
</html>