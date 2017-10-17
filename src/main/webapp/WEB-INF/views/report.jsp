<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Campaign Report</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
	<%@include file="authheader.jsp" %>
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Documents </span> <span>Go to <a href="<c:url value='/list' />">Users List</a></span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
					        <th>No.</th>
					        <th>File Name</th>
					        <th>Type</th>
					        <th>Device Location</th>
					        <th>Last Campaign Time</th>
					        <th>No Of Campaign Per Day</th>
					        <th>Duration</th>
					        <th>Price</th>
							<th>Campaign Start Time</th>
							<th>Working Hours</th>
						</tr>
			    	</thead>
		    		<tbody>
						<c:forEach items="${documents}" var="doc" varStatus="counter">
						<tr>
							<td>${counter.index + 1}</td>
							<td>${doc.userDocument.name}</td>
							<td>${doc.userDocument.type}</td>
							<td>${doc.device.deviceLocation.devceLocationName} - ${doc.device.deviceName}</td>
							<td>${doc.startTime}</td>
							<td>${doc.device.deviceCategory.numberOfTimesPlayed} times / Day</td>
							<td>${doc.device.deviceCategory.secondsPlayed} Seconds </td>
							<td>${doc.device.deviceCategory.secondsPlayed*doc.device.deviceCategory.price*doc.device.deviceCategory.numberOfTimesPlayed}</td>
							<td>    ${doc.device.deviceCategory.dayStartHour} AM</td>
							<td>${doc.device.deviceCategory.workingHours}Hrs/day</td>
						 </tr>
					</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
		</div>
	 	<div class="well floatRight">
	 	<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
	 		Go to <a href="<c:url value='/companylist' />">Company List</a>
	 	</sec:authorize>
	 	</div>
   	</div>
</body>
</html>