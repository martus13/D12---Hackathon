<%--
 * index.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<security:authorize access="isAuthenticated()">
	<spring:message code="book.exchangeRate" />
	<select name="selectExchangeRate" onchange="selectRate(this.value)">
		<option value="1.0" label="Euros (EUR)" />
		<jstl:forEach var="exchangeRate" items="${exchangeRates }">
			<option value="${exchangeRate.value1EUR }" label="${exchangeRate.currency } (${exchangeRate.isoCode })" />
		</jstl:forEach>
	</select>
</security:authorize>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 

<p><spring:message code="welcome.cookies" /></p>

<img src="${banner.picture }" style="max-height: 200px;" /><br>

<security:authorize access="hasRole('USER')">
	<spring:message code="welcome.flightsMostBooked" />:
	<display:table name="${flightsMostBooked }" id="row" requestURI="${requestURI }" class="displaytag" pagesize="5">
				
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
		
		<spring:message code="finder.price" var="priceHeader" />
		<display:column title="${priceHeader }" sortable="true" >
			<jstl:choose>
				<jstl:when test="${finder.isBusiness}">
					<jstl:set var="flightPrice" value="${row[0].businessPrice }" />
				</jstl:when>
				<jstl:otherwise>
					<jstl:set var="flightPrice" value="${row[0].economyPrice }" />
				</jstl:otherwise>
			</jstl:choose>
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
			<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${flightPrice}" />
		</display:column> 
		<display:column>
			<jstl:if test="${not empty row[2] }">
				<font size="4" color="black"><b>-<jstl:out value="${row[2].discount } %" /></b></font>
			</jstl:if>
			<jstl:if test="${empty row[2] && not empty row[3] }">
				<font size="4" color="black"><b>-<jstl:out value="${row[3].discount } %" /></b></font>
			</jstl:if>
		</display:column>
		
		<jstl:if test="${row[4]!=0 }">
			<jstl:set var="checkBooking" value="${false }" />
		</jstl:if>
		
	</display:table>
</security:authorize>

<script>
	 var z=[];
	 var currencyColumn=5;
	 
	 function getValorIni(){
		var iniVal = document.getElementById("row").rows;
		var j;
		
		for(j=0;j<iniVal.length;j++){
			//alert(iniVal[j+1].cells[currencyColumn].innerHTML);
			var varZ = iniVal[j+1].cells[currencyColumn].innerHTML.replace(',', '.');
			z[j] = varZ;
		}
		
	}
	document.onload=getValorIni();
	
	function selectRate(value1EUR){
		var x = document.getElementById("row").rows;
		var i;
		
		for(i=0; i<z.length; i++){
			var y = z[i];
			
			y = Math.round(y*parseFloat(value1EUR) * 100.0) / 100.0;
			
			var y1 = y+"";
			y1 = y1.replace('.', ',');
		
			x[i+1].cells[currencyColumn].innerHTML = y1;
		
		}
	
	}
</script>
