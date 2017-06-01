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

<div>
	<ul>
		<li>
			<b><spring:message code="flight.departureDate" />:</b>
			<fmt:formatDate value="${flight.departureDate}" pattern="dd/MM/yyyy HH:mm" />
		</li>
		
		<li>
			<b><spring:message code="flight.arrivalDate" />:</b>
			<fmt:formatDate value="${flight.arrivalDate}" pattern="dd/MM/yyyy HH:mm" />
		</li>
		
		<li>
			<b><spring:message code="flight.departure" />:</b>
			<jstl:out value="${flight.departure.iataCode }" /> - <jstl:out value="${flight.departure.city }" />
		</li>
		
		<li>
			<b><spring:message code="flight.destination" />:</b>
			<jstl:out value="${flight.destination.iataCode }" /> - <jstl:out value="${flight.destination.city }" />
		</li>
		
		<li>
			<b><spring:message code="flight.economySeats" />:</b>
			<jstl:out value="${flight.availableEconomySeats}" />
		</li>
		
		<li>
			<b><spring:message code="flight.economyPrice" />:</b>
			<font id="economyPrice"><jstl:out value="${flight.economyPrice}" /></font>
		</li>
		
		<li>
			<b><spring:message code="flight.businessSeats"/>:</b>
			<jstl:out value="${flight.availableBusinessSeats}"/>
		</li>
		
		<li>
			<b><spring:message code="flight.businessPrice"/>:</b>
			<font id="businessPrice"><jstl:out value="${flight.businessPrice}"/></font>
		</li>
		
		<security:authorize access="hasRole('MANAGER')">
			<jstl:if test="${flight.airline.id==manager.airline.id }">
				<li>
					<b><spring:message code="flight.books"/>:</b>
					<display:table name="books" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
						<acme:column code="flight.book.creationMoment" property="creationMoment" format="{0,date,dd/MM/yyyy}" />
						<acme:column code="flight.book.passengersNumber" property="passengersNumber" />
						<acme:column code="flight.book.childrenNumber" property="childrenNumber" />
						<spring:message code="flight.book.isBusiness" var="isBusinessHeader" />
						<display:column title="${isBusinessHeader}" sortable="true">
							<jstl:choose>
								<jstl:when test="${row.isBusiness}">
									<spring:message code="flight.book.yes" />
								</jstl:when>
								<jstl:otherwise>
									<spring:message code="flight.book.no" />
								</jstl:otherwise>
							</jstl:choose>
						</display:column>
						<acme:column code="flight.book.totalFee" property="totalFee" />
						<acme:column code="flight.book.comment" property="comment" />
						<acme:column code="flight.book.cancelationMoment" property="cancelationMoment" format="{0,date,dd/MM/yyyy}" />
						<acme:column code="flight.book.user" property="user.name"/>
					</display:table>
				</li>
			</jstl:if>
		</security:authorize>
		
	</ul>
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${flight.airline.id==manager.airline.id }">
			<jstl:if test="${hasBooks }">
				<a href="flight/manager/edit.do?flightId=${flight.id}"><spring:message code="flight.edit" /></a>
			</jstl:if>
			<jstl:set var="now" value="<%=new java.util.Date()%>" />
			<jstl:if test="${flight.departureDate gt now}">
				<form:form action="flight/manager/delete.do?flightId=${flight.id}" modelAttribute="flight">
					<acme:submit name="delete" code="flight.delete" />
				</form:form>
			</jstl:if>
		</jstl:if>
	</security:authorize>
</div>

<script>
	 var z=[];
	 var w;
	 var v;
	 var currencyColumn=4;
	 
	 function getValorIni(){
		var iniVal = document.getElementById("row").rows;
		var iniVal2 = document.getElementById("economyPrice");
		var iniVal3 = document.getElementById("businessPrice");
		var j;
		
		w = iniVal2.innerHTML.replace(',', '.');
		v = iniVal3.innerHTML.replace(',', '.');
		
		for(j=0;j<iniVal.length;j++){
			z[j] = iniVal[j+1].cells[currencyColumn].innerHTML.replace(',', '.');
		}
		
	}
	document.onload=getValorIni();
	
	function selectRate(value1EUR){
		var x = document.getElementById("row").rows;
		var vEconomyPrice = document.getElementById("economyPrice");
		var vBusinessPrice = document.getElementById("businessPrice");
		var i;
		
		var v1 = Math.round(w*value1EUR * 100.0) / 100.0;
		vEconomyPrice.innerHTML = v1;
		var v2 = Math.round(v*value1EUR * 100.0) / 100.0;
		vBusinessPrice.innerHTML = v2;
		
		for(i=0; i<z.length; i++){
			var y = z[i];
			
			y = Math.round(y*value1EUR * 100.0) / 100.0;
		
			x[i+1].cells[currencyColumn].innerHTML = y;
		
		}
	
	}
</script>
	