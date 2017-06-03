<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="offer/manager/edit.do" modelAttribute="offer">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:input code="offer.startMoment" path="startMoment" placeholder="dd/MM/yyyy HH:mm:ss"/>
	<acme:input code="offer.endMoment" path="endMoment" placeholder="dd/MM/yyyy HH:mm:ss"/>
	<acme:input code="offer.discount" path="discount" />
	
    <display:table name="flights" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
  		<display:column>
			<form:checkbox path="offertables" value="${flights}"/>
   		</display:column>
   		
   		<acme:column code="finder.departureDate" property="departureDate" format="{0,date,dd/MM/yyyy HH:mm}" />
		<acme:column code="finder.arrivalDate" property="arrivalDate" format="{0,date,dd/MM/yyyy HH:mm}" />
   		
   		<spring:message code="finder.departure" var="departureHeader" />
		<display:column title="${departureHeader}" sortable="true" >
			<jstl:out value="${row.departure.iataCode }" /> - <jstl:out value="${row.departure.city }" />
		</display:column> 
   
   		<spring:message code="finder.destination" var="destinationHeader" />
		<display:column title="${destinationHeader}" sortable="true" >
			<jstl:out value="${row.destination.iataCode }" /> - <jstl:out value="${row.destination.city }" />
		</display:column> 
   		
   		<spring:message code="flight.businessPrice" var="priceHeader" />
		<display:column title="${priceHeader }" sortable="true" >
			<jstl:out value="${row.businessPrice}" />
		</display:column> 
		
	<spring:message code="flight.economyPrice" var="priceHeader" />
	<display:column title="${priceHeader}" sortable="true" >
			<jstl:out value="${row.economyPrice}" />
		</display:column> 
   		
   		
    </display:table>
	<form:errors path="offertables" cssClass="error" />
	

	
	<acme:submit name="save" code="offer.save" />
	<acme:cancel url="offer/manager/list.do" code="offer.cancel" />
		
</form:form>
