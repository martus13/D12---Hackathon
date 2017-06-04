<%--
 * action-1.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="campaign/manager/edit.do" modelAttribute="campaign">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="airline"/>
	<form:hidden path="deleted"/>
	
	<acme:input code="campaign.name" path="name" />
	<acme:input code="campaign.startDate" path="startDate" disabled="${disabledReturnDate }" placeholder="dd/MM/yyyy HH:mm:ss"/>
	<acme:input code="campaign.endDate" path="endDate" disabled="${disabledReturnDate }" placeholder="dd/MM/yyyy HH:mm:ss"/>
	<acme:input code="campaign.maxDisplayed" path="maxDisplayed"/>
	
	<acme:submit name="save" code="campaign.save" />
	
	<acme:cancel url="campaign/manager/list.do" code="campaign.cancel" />
	
</form:form>