<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="book.exchangeRate" />
<select name="selectExchangeRate" onchange="selectRate(this.value)">
	<option value="1.0" label="Euros (EUR)" />
	<jstl:forEach var="exchangeRate" items="${exchangeRates }">
		<option value="${exchangeRate.value1EUR }" label="${exchangeRate.currency } (${exchangeRate.isoCode })" />
	</jstl:forEach>
</select>

<fieldset>
	<legend>
		<spring:message code="applies.pointsCard" />1
	</legend>
	<display:table name="${applies1.flight }" id="row" requestURI="${requestURI }" class="displaytag">
		
		<acme:column code="applies.flight.departureDate" property="departureDate" format="{0,date,dd/MM/yyyy}" />
		<acme:column code="applies.flight.arrivalDate" property="arrivalDate" format="{0,date,dd/MM/yyyy}" />
		
		<spring:message code="applies.flight.departure" var="departureHeader" />
		<display:column title="${departureHeader}" sortable="true" >
			<jstl:out value="${row.departure.iataCode }" /> - <jstl:out value="${row.departure.city }" />
		</display:column> 
		
		<spring:message code="applies.flight.destination" var="destinationHeader" />
		<display:column title="${destinationHeader}" sortable="true" >
			<jstl:out value="${row.destination.iataCode }" /> - <jstl:out value="${row.destination.city }" />
		</display:column> 
		
		<acme:column code="applies.flight.airline" property="airline.name"/>
	</display:table>
	
	<form:form action="${requestURI }" modelAttribute="applies1">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="book" />
		<form:hidden path="flight" />
		<form:hidden path="pointsCard" />
		
		<acme:input code="applies.usedPoints" path="usedPoints" type="number" min="0" max="${applies1.pointsCard.points }" />
		
		<acme:submit name="save" code="applies.save" />
			
	</form:form>
</fieldset>

<fieldset id="profileData">
	<legend>
		<spring:message code="applies.pointsCard" />2
	</legend>
	<display:table name="${applies2.flight }" id="row" requestURI="${requestURI }" class="displaytag">
				
		<acme:column code="applies.flight.departureDate" property="departureDate" format="{0,date,dd/MM/yyyy}" />
		<acme:column code="applies.flight.arrivalDate" property="arrivalDate" format="{0,date,dd/MM/yyyy}" />
		
		<spring:message code="applies.flight.departure" var="departureHeader" />
		<display:column title="${departureHeader}" sortable="true" >
			<jstl:out value="${row.departure.iataCode }" /> - <jstl:out value="${row.departure.city }" />
		</display:column> 
		
		<spring:message code="applies.flight.destination" var="destinationHeader" />
		<display:column title="${destinationHeader}" sortable="true" >
			<jstl:out value="${row.destination.iataCode }" /> - <jstl:out value="${row.destination.city }" />
		</display:column> 
		
		<acme:column code="applies.flight.airline" property="airline.name"/>
	</display:table>
	<form:form action="${requestURI }" modelAttribute="applies2">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="book" />
		<form:hidden path="flight" />
		<form:hidden path="pointsCard" />
		
		<acme:input code="applies.usedPoints" path="usedPoints" type="number" min="0" max="${applies2.pointsCard.points }" />
		
		<acme:submit name="save" code="applies.save" />
			
	</form:form>
</fieldset>
<font size="5" color="red"><b><spring:message code="book.totalFee" />&nbsp;=&nbsp;<font id="totalFee"><jstl:out value="${book.totalFee }"/></font><br></b></font>
<acme:cancel url="book/user/listByUser.do" code="applies.terminate" />

<script>
	 var z;
	 var currencyColumn=5;
	 
	 function getValorIni(){
		var totalFee = document.getElementById("totalFee").innerHTML;
		
		z = totalFee;
		
	}
	document.onload=getValorIni();
	
	function selectRate(value1EUR){
		document.getElementById("totalFee").innerHTML = Math.round(z*value1EUR * 100.0) / 100.0;
	
	}
</script>

