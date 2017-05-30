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

<security:authentication var="principalUserAccount" property="principal" />
<display:table name="books" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="book.creationMoment" property="creationMoment" format="{0,date,dd/MM/yyyy}" />
	<acme:column code="book.passengersNumber" property="passengersNumber" />
	<acme:column code="book.childrenNumber" property="childrenNumber" />
	<acme:column code="book.isBusiness" property="isBusiness" />
	<spring:message code="book.isBusiness" var="isBusinessHeader" />
	<display:column title="${isBusinessHeader}" sortable="true">
		<jstl:choose>
			<jstl:when test="${row.isBusiness}">
				<spring:message code="book.yes" />
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="book.no" />
			</jstl:otherwise>
		</jstl:choose>
	</display:column>
	<acme:column code="book.totalFee" property="totalFee" />
	<acme:column code="book.comment" property="comment" />
	<acme:column code="book.cancelationMoment" property="cancelationMoment" format="{0,date,dd/MM/yyyy}" />
	
	<display:column sortable="false">
		<a href="book/user/display.do?bookId=${row.id}">
			<spring:message code="book.display" />
		</a>
	</display:column>
	
	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if test="${row.user.userAccount.id == principalUserAccount.id}">
				<form:form action="book/user/delete.do?bookId=${row.id}" modelAttribute="book">
					<acme:submit name="delete" code="book.delete" />
				</form:form>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>

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

