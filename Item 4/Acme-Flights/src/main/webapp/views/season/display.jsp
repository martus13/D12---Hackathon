<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
	<ul>
		<li>
			<b><spring:message code="season.title" />:</b>
			<jstl:out value="${season.title}" />
		</li>
		
		<li>
			<b><spring:message code="season.startDay" />:</b>
			<jstl:out value="${season.startDay}" />
		</li>
		
		<li>
			<b><spring:message code="season.startMonth" />:</b>
			<jstl:out value="${season.startMonth}" />
		</li>
		
		<li>
			<b><spring:message code="season.endDay" />:</b>
			<jstl:out value="${season.endDay}" />
		</li>
		
		<li>
			<b><spring:message code="season.endMonth"/>:</b>
			<jstl:out value="${season.endMonth}"/>
		</li>
		
		<li>
			<b><spring:message code="season.type"/>:</b>
			<jstl:out value="${season.type}"/>
		</li>
		
		<li>
			<b><spring:message code="season.pricePercentage"/>:</b>
			<jstl:out value="${season.pricePercentage}"/>
		</li>
		
		<security:authorize access="!hasRole('MANAGER')" >
			<li>
				<b><spring:message code="season.airline"/>:</b>
				<jstl:out value="${season.airline}"/>
			</li>
		</security:authorize>
		
	</ul>
</div>
	