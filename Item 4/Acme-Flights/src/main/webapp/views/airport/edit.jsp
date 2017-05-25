<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="airport/administrator/edit.do" modelAttribute="airport">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="deleted" />
	<form:hidden path="outgoings" />
	
	<acme:input code="airport.iataCode" path="iataCode" />
	<acme:input code="airport.airportName" path="airportName" />
	<acme:input code="airport.city" path="city" />
	<acme:input code="airport.state" path="state" />
	<acme:input code="airport.province" path="province" />
	<acme:input code="airport.country" path="country" />
	<acme:input code="airport.rate" path="rate" type="number" min="0" step="0.01" />
		 
	<acme:submit name="save" code="airport.save" />
	<acme:cancel url="airport/administrator/list.do" code="airport.cancel" />
		
</form:form>