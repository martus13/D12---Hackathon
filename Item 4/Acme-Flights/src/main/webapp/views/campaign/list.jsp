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

<display:table name="campaigns" id="row" requestURI="campaign/sponsor/list.do">

	<acme:column code="campaign.startDate" property="startDate" format="{0,date,dd/MM/yyyy}" />
	<acme:column code="campaign.endDate" property="endDate" format="{0,date,dd/MM/yyyy}" />
	<acme:column code="campaign.maxDisplayed" property="maxDisplayed" />
	<acme:column code="campaign.airline" property="airline" />

	<display:column>
		<a href="campaign/manager/edit.do?campaignId=${row.id }"><spring:message code="campaign.edit" /></a>
	</display:column>
	<display:column>
		<form:form action="campaign/manager/delete.do?campaignId=${row.id}" modelAttribute="campaign">
			<acme:submit name="delete" code="campaign.delete" />
		</form:form>
	</display:column>
		
</display:table>
