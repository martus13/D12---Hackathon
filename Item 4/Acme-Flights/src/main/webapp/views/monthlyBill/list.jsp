<%--
 * action-1.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
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

<spring:message code="monthlyBill.exchangeRate" />
<select name="selectExchangeRate" onchange="selectRate(this.value)">
	<option value="1.0" label="Euros (EUR)" />
	<jstl:forEach var="exchangeRate" items="${exchangeRates }">
		<option value="${exchangeRate.value1EUR }" label="${exchangeRate.currency } (${exchangeRate.isoCode })" />
	</jstl:forEach>
</select>

<jstl:if test="${errorUnpaid }">
	<br><b><font size="4" color="red"><spring:message code="monthlyBills.unpaid.error" /></font></b>
</jstl:if>

<display:table name="monthlyBills" id="row" requestURI="${requestURI }" >
	
	<acme:column code="monthlyBill.creationMoment" property="creationMoment" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
	<acme:column code="monthlyBill.paidMoment" property="paidMoment" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
	<acme:column code="monthlyBill.totalFee" property="totalFee" />
	<acme:column code="monthlyBill.description" property="description" />
	<acme:column code="monthlyBill.campaign" property="campaign.name" />
		
</display:table>
<%-- <security:authorize access="hasRole('ADMIN')">
	<form:form action="monthlyBill/administrator/create.do" modelAttribute="monthlyBill">
		<input type="submit" name="monthlyBills" value="<spring:message code="monthlyBill.compute" />" />
	</form:form>
</security:authorize>
 --%>
 
 <script>
	 var z=[];
	 var currencyColumn=2;
	 
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
 