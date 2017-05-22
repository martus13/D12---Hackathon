<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authentication var="principalUserAccount" property="principal" />
<jstl:set var="i" value="1" />

<jstl:forEach var="flight" items="${flights }">
	
	<fieldset id="profileData" disabled>
		<legend>
			<spring:message code="finder.option" />&nbsp;<jstl:out value="${i }" />
		</legend>
		
		<display:table name="${flight }" id="row" requestURI="${requestURI }" class="displaytag">
			
			<acme:column code="finder.departureDate" property="departureDate" />
			<acme:column code="finder.arrivalDate" property="arrivalDate" />
			
			<spring:message code="finder.departure" var="departureHeader" />
			<display:column title="${departureHeader}" sortable="true" >
				<jstl:out value="${row.departure.iataCode }" /> - <jstl:out value="${row.departure.city }" />
			</display:column> 
			
			<spring:message code="finder.destination" var="destinationHeader" />
			<display:column title="${destinationHeader}" sortable="true" >
				<jstl:out value="${row.destination.iataCode }" /> - <jstl:out value="${row.destination.city }" />
			</display:column> 
			
			<acme:column code="finder.airline" property="airline.name" />
		</display:table>
		<a href="book/user/create.do?departureId=${flight[0].id}&&destinationId=${flight[1].id}"><spring:message code="finder.book" /></a>
		<jstl:set var="i" value="${i+1 }" />
	</fieldset>
</jstl:forEach>

