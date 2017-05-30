<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="flights" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="flight.departureDate" property="departureDate" format="{0,date,dd/MM/yyyy HH:mm}" />
	<acme:column code="flight.arrivalDate" property="arrivalDate" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	<spring:message code="flight.departure" var="departureHeader" />
	<display:column title="${departureHeader}" sortable="true" >
		<jstl:out value="${row.departure.iataCode }" /> - <jstl:out value="${row.departure.city }" />
	</display:column> 
	
	<spring:message code="flight.destination" var="destinationHeader" />
	<display:column title="${destinationHeader}" sortable="true" >
		<jstl:out value="${row.destination.iataCode }" /> - <jstl:out value="${row.destination.city }" />
	</display:column> 
	
	<display:column>
		<a href="airline/display.do?airlineId=${row.airline.id }"><spring:message code="flight.display" /></a>
	</display:column>
	
	<display:column>
		<a href="flight/manager/display.do?flightId=${row.id }"><jstl:out value="${row.airline.name }" /></a>
	</display:column>
	
</display:table>
<security:authorize access="hasRole('MANAGER')">
	<a href="flight/manager/create.do"><spring:message code="flight.create" /></a>
</security:authorize>

