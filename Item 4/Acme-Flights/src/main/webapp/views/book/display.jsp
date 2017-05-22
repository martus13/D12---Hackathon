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
			<b><spring:message code="book.creationMoment" />:</b>
			<fmt:formatDate value="${book.creationMoment}" pattern="dd/MM/yyyy HH:mm:ss" />
		</li>
		
		<li>
			<b><spring:message code="book.passengersNumber" />:</b>
			<jstl:out value="${book.passengersNumber}" />
		</li>
		
		<li>
			<b><spring:message code="book.childrenNumber" />:</b>
			<jstl:out value="${book.childrenNumber}" />
		</li>
		
		<li>
			<b><spring:message code="book.isBusiness" />:</b>
			<jstl:out value="${book.isBusiness}" />
		</li>
		
		<li>
			<b><spring:message code="book.totalFee"/>:</b>
			<jstl:out value="${book.totalFee}"/>
		</li>
		
		<li>
			<b><spring:message code="book.comment"/>:</b>
			<jstl:out value="${book.comment}"/>
		</li>
		
		<li>
			<b><spring:message code="book.cancelationMoment"/>:</b>
			<fmt:formatDate value="${book.cancelationMoment}" pattern="dd/MM/yyyy" />
		</li>
		
		<li>
			<b><spring:message code="book.flights"/>:</b>
			<display:table name="${book.flights }" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
				
				<acme:column code="book.flight.departureDate" property="departureDate" format="{0,date,dd/MM/yyyy}" />
				<acme:column code="book.flight.arrivalDate" property="arrivalDate" format="{0,date,dd/MM/yyyy}" />
				
				<spring:message code="book.flight.departure" var="departureHeader" />
				<display:column title="${departureHeader}" sortable="true" >
					<jstl:out value="${row.departure.iataCode }" /> - <jstl:out value="${row.departure.city }" />
				</display:column> 
				
				<spring:message code="book.flight.destination" var="destinationHeader" />
				<display:column title="${destinationHeader}" sortable="true" >
					<jstl:out value="${row.destination.iataCode }" /> - <jstl:out value="${row.destination.city }" />
				</display:column> 
			</display:table>
		</li>
		
	</ul>
</div>
	