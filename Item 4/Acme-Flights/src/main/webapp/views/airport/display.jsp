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

<div>
	<ul>
		<li>
			<b><spring:message code="airport.iataCode" />:</b>
			<jstl:out value="${airport.iataCode}" />
		</li>
		
		<li>
			<b><spring:message code="airport.airportName" />:</b>
			<jstl:out value="${airport.airportName}" />
		</li>
		
		<li>
			<b><spring:message code="airport.city" />:</b>
			<jstl:out value="${airport.city}" />
		</li>
		
		<jstl:if test="${not empty airport.province}">
			<li>
				<b><spring:message code="airport.province" />:</b>
				<jstl:out value="${airport.province}" />
			</li>
		</jstl:if>
		<jstl:if test="${not empty airport.state}">
			<li>
				<b><spring:message code="airport.state"/>:</b>
				<jstl:out value="${airport.state}"/>
			</li>
		</jstl:if>
		
		<li>
			<b><spring:message code="airport.country"/>:</b>
			<jstl:out value="${airport.country}"/>
		</li>
		
		<li>
			<b><spring:message code="airport.rate"/>:</b>
			<font id="airportRate"><jstl:out value="${airport.rate}" /></font>
		</li>
		
	</ul>
</div>

<script>
	 var z;
	 
	 function getValorIni(){
		var iniVal = document.getElementById("airportRate");
		z = iniVal.innerHTML.replace(',', '.');
		
	}
	document.onload=getValorIni();
	
	function selectRate(value1EUR){
		var iniVal = document.getElementById("airportRate");
		var y = Math.round(z*parseFloat(value1EUR) * 100.0) / 100.0;
		var y1 = y+"";
		y1 = y1.replace('.', ',');
		
		iniVal.innerHTML = y1;
		
	}
</script>
	