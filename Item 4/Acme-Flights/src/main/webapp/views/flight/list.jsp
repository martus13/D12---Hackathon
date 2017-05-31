<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:message code="book.exchangeRate" />
<select name="selectExchangeRate" onchange="selectRate(this.value)">
	<option value="1.0" label="Euros (EUR)" />
	<jstl:forEach var="exchangeRate" items="${exchangeRates }">
		<option value="${exchangeRate.value1EUR }" label="${exchangeRate.currency } (${exchangeRate.isoCode })" />
	</jstl:forEach>
</select>

<display:table name="${flights }" id="row" requestURI="${requestURI }" class="displaytag">
			
	<acme:column code="finder.departureDate" property="[0].departureDate" format="{0,date,dd/MM/yyyy HH:mm}" />
	<acme:column code="finder.arrivalDate" property="[0].arrivalDate" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	<spring:message code="finder.departure" var="departureHeader" />
	<display:column title="${departureHeader}" sortable="true" >
		<jstl:out value="${row[0].departure.iataCode }" /> - <jstl:out value="${row[0].departure.city }" />
	</display:column> 
	
	<spring:message code="finder.destination" var="destinationHeader" />
	<display:column title="${destinationHeader}" sortable="true" >
		<jstl:out value="${row[0].destination.iataCode }" /> - <jstl:out value="${row[0].destination.city }" />
	</display:column> 
	
	<spring:message code="finder.airline" var="airlineHeader" />
	<display:column title="${airlineHeader}" sortable="true" >
		<a href="airline/display.do?airlineId=${row[0].airline.id }"><jstl:out value="${row[0].airline.name }" /></a>
	</display:column>
	
	<spring:message code="flight.businessPrice" var="priceHeader" />
	<display:column title="${priceHeader }" sortable="true" >
		<jstl:set var="flightPrice" value="${row[0].businessPrice }" />
		<security:authorize access="!hasRole('MANAGER')">
			<jstl:if test="${not empty row[1]}">
				<jstl:choose>
					<jstl:when test="${row[1].type=='increase' }">
						<jstl:set var="flightPrice" value="${flightPrice+(flightPrice*row[1].pricePercentage/100) }" />
					</jstl:when>
					<jstl:otherwise>
						<jstl:set var="flightPrice" value="${flightPrice-(flightPrice*row[1].pricePercentage/100) }" />
					</jstl:otherwise>
				</jstl:choose>
			</jstl:if>
		</security:authorize>
		<security:authorize access="hasRole('MANAGER')">
			<jstl:if test="${(empty manager || manager.airline.id!=row[0].airline.id) && not empty row[1] }">
				<jstl:choose>
					<jstl:when test="${row[1].type=='increase' }">
						<jstl:set var="flightPrice" value="${flightPrice+(flightPrice*row[1].pricePercentage/100) }" />
					</jstl:when>
					<jstl:otherwise>
						<jstl:set var="flightPrice" value="${flightPrice-(flightPrice*row[1].pricePercentage/100) }" />
					</jstl:otherwise>
				</jstl:choose>
			</jstl:if>
		</security:authorize>
		
		<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${flightPrice}" />
	</display:column>  
	
	<spring:message code="flight.economyPrice" var="priceHeader" />
	<display:column title="${priceHeader }" sortable="true" >
		<jstl:set var="flightPrice" value="${row[0].economyPrice }" />
		<security:authorize access="!hasRole('MANAGER')">
			<jstl:if test="${not empty row[1]}">
				<jstl:choose>
					<jstl:when test="${row[1].type=='increase' }">
						<jstl:set var="flightPrice" value="${flightPrice+(flightPrice*row[1].pricePercentage/100) }" />
					</jstl:when>
					<jstl:otherwise>
						<jstl:set var="flightPrice" value="${flightPrice-(flightPrice*row[1].pricePercentage/100) }" />
					</jstl:otherwise>
				</jstl:choose>
			</jstl:if>
		</security:authorize>
		<security:authorize access="hasRole('MANAGER')">
			<jstl:if test="${(empty manager || manager.airline.id!=row[0].airline.id) && not empty row[1] }">
				<jstl:choose>
					<jstl:when test="${row[1].type=='increase' }">
						<jstl:set var="flightPrice" value="${flightPrice+(flightPrice*row[1].pricePercentage/100) }" />
					</jstl:when>
					<jstl:otherwise>
						<jstl:set var="flightPrice" value="${flightPrice-(flightPrice*row[1].pricePercentage/100) }" />
					</jstl:otherwise>
				</jstl:choose>
			</jstl:if>
		</security:authorize>
		
		<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${flightPrice}" />
	</display:column> 
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${not empty manager }">
			<spring:message code="flight.season" var="seasonHeader" />
			<display:column title="${seasonHeader }">
				<jstl:if test="${not empty row[1] && manager.airline.id==row[0].airline.id}">
					<jstl:choose>
						<jstl:when test="${row[1].type=='increase' }">
							<jstl:out value="+${row[1].pricePercentage }%" />
						</jstl:when>
						<jstl:otherwise>
							<jstl:out value="-${row[1].pricePercentage }%" />
						</jstl:otherwise>
					</jstl:choose>
				</jstl:if>
			</display:column>
		</jstl:if>
	</security:authorize>
	
	<spring:message code="flight.offer" var="offerHeader" />
	<display:column title="${offerHeader }">
		<jstl:if test="${not empty row[2] }">
			<font size="4" color="black"><b>-<jstl:out value="${row[2].discount } %" /></b></font>
		</jstl:if>
		<jstl:if test="${empty row[2] && not empty row[3] }">
			<font size="4" color="black"><b>-<jstl:out value="${row[3].discount } %" /></b></font>
		</jstl:if>
	</display:column>
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${not empty manager }">
			<display:column>
				<jstl:if test="${manager.airline.id==row[0].airline.id }">
					<a href="flight/manager/display.do?flightId=${row[0].id }"><spring:message code="flight.display" /></a>
				</jstl:if>
			</display:column>
		</jstl:if>
	</security:authorize>
	
</display:table>
<security:authorize access="hasRole('MANAGER')">
	<a href="flight/manager/create.do"><spring:message code="flight.create" /></a>
</security:authorize>

<script>
	 var z=[];
	 var currencyColumn=5;
	 
	 function getValorIni(){
		var iniVal = document.getElementById("row").rows;
		var j;
		
		for(j=0;j<iniVal.length;j++){
			z[j] = iniVal[j+1].cells[currencyColumn].innerHTML;
		}
		
	}
	document.onload=getValorIni();
	
	function selectRate(value1EUR){
		var x = document.getElementById("row").rows;
		var i;
		
		for(i=0; i<z.length; i++){
			var y = z[i];
			
			y = Math.round(y*value1EUR * 100.0) / 100.0;
		
			x[i+1].cells[currencyColumn].innerHTML = y;
		
		}
	
	}
</script>


