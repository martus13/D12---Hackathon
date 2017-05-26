<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form method="post" action="finder/user/edit.do" modelAttribute="finder" >
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="user"/>
	<form:hidden path="updatedMoment"/>
	
	<div>
		<form:label path="departure">
			<spring:message code="finder.departure" />
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
			<spring:message code="finder.destination" />
		</form:label>	
		<form:select path="destination">
			<form:option value="${null }" label="----" />		
			<jstl:forEach items="${airports }" var="destination">
				<form:option value="${destination}" label="${destination.iataCode} - ${destination.city }" />
			</jstl:forEach>
		</form:select>
		<form:errors path="destination" cssClass="error" />
	</div>
	
	<acme:input code="finder.departureDate" path="departureDate" placeholder="dd/mm/yyyy"/>
	<jstl:choose>
		<jstl:when test="${finder.id==0 }">
			<jstl:set var="disabledReturnDate" value="${false}" />
			<jstl:set var="checkedReturnFlight" value="${true}" />
		</jstl:when>
		<jstl:otherwise>
			<jstl:set var="disabledReturnDate" value="${not finder.returnFlight}" /> 
		</jstl:otherwise>
	</jstl:choose>
	<acme:checkbox code="finder.returnFlight" path="returnFlight" checked="${checkedReturnFlight }" onchange="myFunctionYes(this.checked)" />
	<acme:input code="finder.returnDate" path="returnDate" disabled="${disabledReturnDate }" placeholder="dd/mm/yyyy"/>
	<acme:input code="finder.passengersNumber" path="passengersNumber" />
	<acme:input code="finder.childrenNumber" path="childrenNumber" />
	<acme:checkbox code="finder.isBusiness" path="isBusiness" />
	
	<acme:submit name="save" code="finder.save" />
	<jstl:if test="${finder.id!=0 }">
		<acme:submit name="delete" code="finder.delete" />
	</jstl:if>
	<acme:cancel url="finder/user/display.do" code="finder.cancel" />
	
</form:form>

<script>
	
	function myFunctionYes(value) {
    	if(value){
    		document.getElementsByName("returnDate")[0].disabled = false;
    	}else{
    		document.getElementsByName("returnDate")[0].disabled = true;
    	}
	}
</script>
	