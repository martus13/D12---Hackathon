<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="flight/manager/create.do" modelAttribute="flight">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="creationMoment" />
	<form:hidden path="availableBusinessSeats" />
	<form:hidden path="availableEconomySeats" />
	<form:hidden path="cancelled" />
	<form:hidden path="airline" />
	
	<acme:input code="flight.departureDate" path="departureDate" placeholder="dd/MM/yyyy HH:mm" />
	<acme:input code="flight.arrivalDate" path="arrivalDate" placeholder="dd/MM/yyyy HH:mm" />
	<acme:input code="flight.economySeats" path="economySeats" type="number" min="0" />
	<acme:input code="flight.economyPrice" path="economyPrice" type="number" min="0" step="0.01" />
	<acme:input code="flight.businessSeats" path="businessSeats" type="number" min="0" />
	<acme:input code="flight.businessPrice" path="businessPrice" type="number" min="0" step="0.01" />
	
	<div>
		<form:label path="departure">
			<spring:message code="flight.departure" />
		</form:label>	
		<form:select path="departure">
			<form:option value="${null }" label="----" />		
			<jstl:forEach items="${airports }" var="departure">
				<form:option value="${departure}" label="${departure.iataCode} - ${departure.city }" />
			</jstl:forEach>
		</form:select>
		<form:errors path="departure" cssClass="error" />
	</div>
	
	<div>
		<form:label path="destination">
			<spring:message code="flight.destination" />
		</form:label>	
		<form:select path="destination">
			<form:option value="${null }" label="----" />		
			<jstl:forEach items="${airports }" var="destination">
				<form:option value="${destination}" label="${destination.iataCode} - ${destination.city }" />
			</jstl:forEach>
		</form:select>
		<form:errors path="destination" cssClass="error" />
	</div>
	
	<acme:submit name="save" code="flight.save" />
	<acme:cancel url="flight/manager/listByAirline.do" code="flight.cancel" />
		
</form:form>
