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
			<b><spring:message code="flight.departureDate" />:</b>
			<fmt:formatDate value="${flight.departureDate}" pattern="dd/MM/yyyy HH:mm" />
		</li>
		
		<li>
			<b><spring:message code="flight.arrivalDate" />:</b>
			<fmt:formatDate value="${flight.arrivalDate}" pattern="dd/MM/yyyy HH:mm" />
		</li>
		
		<li>
			<b><spring:message code="flight.departure" />:</b>
			<jstl:out value="${flight.departure.iataCode }" /> - <jstl:out value="${flight.departure.city }" />
		</li>
		
		<li>
			<b><spring:message code="flight.destination" />:</b>
			<jstl:out value="${flight.destination.iataCode }" /> - <jstl:out value="${flight.destination.city }" />
		</li>
		
		<li>
			<b><spring:message code="flight.economySeats" />:</b>
			<jstl:out value="${flight.economySeats}" />
		</li>
		
		<li>
			<b><spring:message code="flight.economyPrice" />:</b>
			<jstl:out value="${flight.economyPrice}" />
		</li>
		
		<li>
			<b><spring:message code="flight.businessSeats"/>:</b>
			<jstl:out value="${flight.businessSeats}"/>
		</li>
		
		<li>
			<b><spring:message code="flight.businessPrice"/>:</b>
			<jstl:out value="${flight.businessPrice}"/>
		</li>
		
	</ul>
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${flight.airline.id==manager.airline.id }">
			<jstl:if test="${hasBooks }">
				<a href="flight/manager/edit.do?flightId=${flight.id}"><spring:message code="flight.edit" /></a>
			</jstl:if>
			<jstl:set var="now" value="<%=new java.util.Date()%>" />
			<jstl:if test="${flight.departureDate gt now}">
				<form:form action="flight/manager/delete.do?flightId=${flight.id}" modelAttribute="flight">
					<acme:submit name="delete" code="flight.delete" />
				</form:form>
			</jstl:if>
		</jstl:if>
	</security:authorize>
</div>
	