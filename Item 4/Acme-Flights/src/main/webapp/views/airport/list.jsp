<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="airport.exchangeRate" />
<select name="selectExchangeRate" onchange="selectRate(this.value)">
	<option value="1.0" label="Euros (EUR)" />
	<jstl:forEach var="exchangeRate" items="${exchangeRates }">
		<option value="${exchangeRate.value1EUR }" label="${exchangeRate.currency } (${exchangeRate.isoCode })" />
	</jstl:forEach>
</select>

<display:table name="airports" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="airport.iataCode" property="iataCode" />
	<acme:column code="airport.airportName" property="airportName" />
	<acme:column code="airport.city" property="city" />
	<acme:column code="airport.province" property="province" />
	<acme:column code="airport.state" property="state" />
	<acme:column code="airport.country" property="country" />
	
	<security:authorize access="hasRole('ADMIN')" >
		<acme:column code="airport.rate" property="rate" />
	</security:authorize>
	
	<display:column>
		<a href="airport/administrator/display.do?airportId=${row.id }"><spring:message code="airport.display" /></a>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')" >
		<display:column>
			<a href="airport/administrator/edit.do?airportId=${row.id }"><spring:message code="airport.edit" /></a>
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('ADMIN')" >
	<a href="airport/administrator/create.do"><spring:message code="airport.create" /></a>
</security:authorize>

<script>
	 var z=[];
	 var currencyColumn=6;
	 
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

