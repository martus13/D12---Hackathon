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
			<b><spring:message code="configuration.fee" />:</b>
			<font id="fee"><jstl:out value="${configuration.fee}" /></font>
		</li>
	
		<li>
			<b><spring:message code="configuration.campaignFee" />:</b>
			<font id="campaignFee"><jstl:out value="${configuration.campaignFee}" /></font>
		</li>
	
	</ul>
</div>	

<a href="configuration/administrator/edit.do?configurationId=${configuration.id}">
	<spring:message code="configuration.edit" />
</a>

<script>
	 var z;
	 var w;
	 
	 function getValorIni(){
		var iniVal = document.getElementById("fee");
		z = iniVal.innerHTML.replace(',', '.');
		var iniVal = document.getElementById("campaignFee");
		w = iniVal.innerHTML.replace(',', '.');
		
	}
	document.onload=getValorIni();
	
	function selectRate(value1EUR){
		var iniVal = document.getElementById("fee");
		var y = Math.round(z*parseFloat(value1EUR) * 100.0) / 100.0;
		iniVal.innerHTML = y;
		
		var iniVal2 = document.getElementById("campaignFee");
		var y = Math.round(w*parseFloat(value1EUR) * 100.0) / 100.0;
		
		iniVal2.innerHTML = y;
		
	}
</script>

	
	