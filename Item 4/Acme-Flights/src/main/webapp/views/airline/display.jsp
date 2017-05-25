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
			<b><spring:message code="airline.picture" />:</b>
			<img src="${airline.picture}" style="max-height: 120px;" />
		</li>
		
		<li>
			<b><spring:message code="airline.name" />:</b>
			<jstl:out value="${airline.name}" />
		</li>
		
		<li>
			<b><spring:message code="airline.contactPhone" />:</b>
			<jstl:out value="${airline.contactPhone}" />
		</li>
		
		<li>
			<b><spring:message code="airline.email"/>:</b>
			<jstl:out value="${airline.email}"/>
		</li>
		
		<li>
			<b><spring:message code="airline.rating"/>:</b>
			<jstl:out value="${airline.rating}"/>
		</li>
		
	</ul>
</div>
	