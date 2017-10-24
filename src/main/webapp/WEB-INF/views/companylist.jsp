<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Company List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
	<%@include file="authheader.jsp" %>
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Companies </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
					        <th>First Name</th>
					        <th>Last Name</th>
					        <th>Email</th>
					        <th>SSO ID</th>
					        <th>Contact No</th>
					        <th>Company<th>
					        <th width="50"></th>
					        <th width="50"></th>
					        <th width="50"></th>
					        <sec:authorize access="hasRole('ADMIN')">
				        	<th width="100"></th>
				        </sec:authorize>
						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>
							<td>${user.ssoId}</td>
							<td>${user.phone}</td>
							<td>${user.company}</td>
							<td><a href="<c:url value='/reports-${user.id}' />" class="btn btn-blue custom-width">report</a></td>
							<td><a href="<c:url value='/add-document-${user.id}' />" class="btn btn-blue custom-width">campaign</a></td>
							<td><a href="<c:url value='/edit-company-${user.ssoId}' />" class="btn btn-success custom-width">edit</a></td>
							<sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-company-${user.ssoId}-${user.registerUser.id}' />" class="btn btn-danger custom-width">delete</a></td>
				        </sec:authorize>
						</tr>
					</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
		</div>
	 	<div class="well">
	 		<a href="<c:url value='/newcompany-${register}'/>">Add New Company Details</a>
	 	</div>
   	</div>
</body>
</html>