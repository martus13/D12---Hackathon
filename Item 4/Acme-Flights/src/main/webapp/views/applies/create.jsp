<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<fieldset id="profileData">
	<legend>
		<spring:message code="finder.option" />&nbsp;<jstl:out value="${i }" />
	</legend>
	<display:table name="${applies1.flight }" id="row" requestURI="${requestURI }" class="displaytag">
				
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
	
	<form:form action="${requestURI }" modelAttribute="applies1">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="book" />
		<form:hidden path="flight" />
		<form:hidden path="pointsCard" />
		
		<acme:input code="applies.usedPoints" path="usedPoints" type="number" min="0" />
		
		<acme:submit name="save" code="applies.save" />
			
	</form:form>
</fieldset>

<fieldset id="profileData">
	<legend>
		<spring:message code="finder.option" />&nbsp;<jstl:out value="${i }" />
	</legend>
	<display:table name="${applies2.flight }" id="row" requestURI="${requestURI }" class="displaytag">
				
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
	<form:form action="${requestURI }" modelAttribute="applies2">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="book" />
		<form:hidden path="flight" />
		<form:hidden path="pointsCard" />
		
		<acme:input code="applies.usedPoints" path="usedPoints" type="number" min="0" />
		
		<acme:submit name="save" code="applies.save" />
			
	</form:form>
</fieldset>
<spring:message code="book.totalFee" /><jstl:out value="${book.totalFee }"></jstl:out>
<acme:cancel url="book/user/listByUser.do" code="applies.cancel" />
