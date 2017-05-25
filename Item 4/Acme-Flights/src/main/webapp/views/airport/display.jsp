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
			<b><spring:message code="airport.iataCode" />:</b>
			<jstl:out value="${airport.iataCode}" />
		</li>
		
		<li>
			<b><spring:message code="airport.airportName" />:</b>
			<jstl:out value="${airport.airportName}" />
		</li>
		
		<li>
			<b><spring:message code="airport.city" />:</b>
			<jstl:out value="${airport.city}" />
		</li>
		
		<jstl:if test="${not empty airport.province}">
			<li>
				<b><spring:message code="airport.province" />:</b>
				<jstl:out value="${airport.province}" />
			</li>
		</jstl:if>
		<jstl:if test="${not empty airport.state}">
			<li>
				<b><spring:message code="airport.state"/>:</b>
				<jstl:out value="${airport.state}"/>
			</li>
		</jstl:if>
		
		<li>
			<b><spring:message code="airport.country"/>:</b>
			<jstl:out value="${airport.country}"/>
		</li>
		
		<li>
			<b><spring:message code="airport.rate"/>:</b>
			<jstl:out value="${airport.rate}" />
		</li>
		
	</ul>
</div>
	