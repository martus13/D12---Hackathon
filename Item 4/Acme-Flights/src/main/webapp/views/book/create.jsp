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

<form:form action="book/user/create.do" modelAttribute="book">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="creationMoment" />
	<form:hidden path="passengersNumber" />
	<form:hidden path="childrenNumber" />
	<form:hidden path="isBusiness" />
	<form:hidden path="totalFee" />
	<form:hidden path="cancelationMoment" />
	<form:hidden path="user" />
	<form:hidden path="flights" />
	<form:hidden path="seasons" />
	<form:hidden path="offers" />
	
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
		<jstl:choose>
			<jstl:when test="${book.isBusiness }">
				<spring:message code="book.yes" />
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="book.no" />
			</jstl:otherwise>
		</jstl:choose>
	</li>
	<li>
		<b><spring:message code="book.flights" />:</b>
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
			
		</display:table>
	</li>
	
	<li>
		<b><spring:message code="book.totalFee"/>:</b>
		<font id="bookTotalFee"><jstl:out value="${book.totalFee}"/></font>
	</li>
	
	<acme:textarea code="book.comment" path="comment" rows="3" />
	
	<acme:submit name="save" code="book.save" />
	<acme:cancel url="book/user/listByUser.do" code="book.cancel" />
		
</form:form>

<script>
	 var z=[];
	 var currencyColumn=5;
	 
	 function getValorIni(){
		var totalFee = document.getElementById("bookTotalFee").innerHTML;
		var iniVal = document.getElementById("row").rows;
		var j;
		
		z[0] = totalFee;
		for(j=1;j<=iniVal.length;j++){
			z[j] = iniVal[j].cells[currencyColumn].innerHTML;
		}
		
	}
	document.onload=getValorIni();
	
	function selectRate(value1EUR){
		var x = document.getElementById("row").rows;
		var i;
		
		document.getElementById("bookTotalFee").innerHTML = Math.round(z[0]*value1EUR * 100.0) / 100.0;
		for(i=1; i<z.length; i++){
			var y = z[i];
			
			y = Math.round(y*value1EUR * 100.0) / 100.0;
		
			x[i].cells[currencyColumn].innerHTML = y;
		
		}
	
	}
</script>
