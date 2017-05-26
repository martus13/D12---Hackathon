<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	var z = [];
</script>
<security:authentication var="principalUserAccount" property="principal" />
<jstl:set var="i" value="1" />

<spring:message code="finder.exchangeRate" />
<select name="selectExchangeRate" onchange="selectRate(this.value)">
	<option value="1.0" label="Euros (EUR)" />
	<jstl:forEach var="exchangeRate" items="${exchangeRates }">
		<option value="${exchangeRate.value1EUR }" label="${exchangeRate.currency } (${exchangeRate.isoCode })" />
	</jstl:forEach>
</select>

<jstl:forEach var="flight" items="${flights }">
	
	<fieldset id="profileData" disabled>
		<legend>
			<spring:message code="finder.option" />&nbsp;<jstl:out value="${i }" />
		</legend>
		
		<display:table name="${flight }" id="row" requestURI="${requestURI }" class="displaytag">
			
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
			
			<acme:column code="finder.airline" property="[0].airline.name" />
			
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
					<b>-<jstl:out value="${row[2].discount } %" /></b>
				</jstl:if>
				<jstl:if test="${empty row[2] && not empty row[3] }">
					<b>-<jstl:out value="${row[3].discount } %" /></b>
				</jstl:if>
			</display:column>
			
		</display:table>
		
		<jstl:choose>
			<jstl:when test="${finder.returnFlight}">
				<a href="book/user/create.do?departureId=${flight[0][0].id}&&season1=${flight[0][1].id }&&offerFlight1=${flight[0][2].id }&&offerAirline1=${flight[0][3].id }&&destinationId=${flight[1][0].id}&&season2=${flight[1][1].id }&&offerFlight2=${flight[1][2].id }&&offerAirline2=${flight[1][3].id }"><spring:message code="finder.book" /></a>
			</jstl:when>
			<jstl:otherwise>
				<a href="book/user/createWithoutReturn.do?departureId=${flight[0][0].id}&&season1=${flight[0][1].id }&&offerFlight1=${flight[0][2].id }&&offerAirline1=${flight[0][3].id }"><spring:message code="finder.book" /></a>
			</jstl:otherwise>
		</jstl:choose>
		<jstl:if test="${finder.returnFlight }">
			
		</jstl:if>
		
		<jstl:set var="i" value="${i+1 }" />
	</fieldset>
	
	<script>
	var currencyColumn = 5;	
	function getValorIni(){
			var iniVal = document.getElementsByTagName("table");
			var iniValLast = iniVal[iniVal.length-1].rows;
			var y = [];
			
			for(var j=0;j<iniValLast.length;j++){
				str = iniValLast[j+1].cells[currencyColumn].innerHTML;
				y[j]=str;
				if(j==iniValLast.length-2){
					z.push(y);
				}
			}
		}
		document.onload=getValorIni();
		
		function selectRate(value1EUR){
			var x = document.getElementsByTagName("table");
			
			for(var i=0;i<x.length;i++){
				w = z[i];
				for (var l = 0; row = x[i].rows[l+1]; l++) {
					v = w[l];
					
					v = Math.round(v*value1EUR * 100.0) / 100.0;
					
					row.cells[currencyColumn].innerHTML = v;
					
				}
			}
		}
	</script>
</jstl:forEach>

