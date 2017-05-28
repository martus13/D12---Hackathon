<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



	<div>
	<ul>
		<li>
			<b><spring:message code="configuration.fee" />:</b>
			<jstl:out value="${configuration.fee}" />
		</li>
	
		<li>
			<b><spring:message code="configuration.campaignFee" />:</b>
			<jstl:out value="${configuration.campaignFee}" />
		</li>
	
	</ul>
	</div>	

		<a href="configuration/administrator/edit.do?configurationId=${configuration.id}">
			<spring:message code="configuration.edit" />
		</a>

	
	