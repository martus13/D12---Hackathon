<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:message code="invoice.exchangeRate" />
<select name="selectExchangeRate" onchange="selectRate(this.value)">
	<option value="1.0" label="Euros (EUR)" />
	<jstl:forEach var="exchangeRate" items="${exchangeRates }">
		<option value="${exchangeRate.value1EUR }" label="${exchangeRate.currency } (${exchangeRate.isoCode })" />
	</jstl:forEach>
</select>


<security:authorize access="hasRole('ADMIN')">
		<form:form action="invoice/administrator/generateInvoices.do" modelAttribute="invoice">
			<acme:submit name="generate" code="invoice.generate" />
		</form:form>
	</security:authorize>

<display:table name="invoices" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<acme:column code="invoice.totalFee" property="totalFee"/>
	<spring:message code="invoice.paidMoment" var="paidMomentHeader"/>
	<display:column title="${paidMomentHeader}" sortable="true" >
		<jstl:choose>
		<jstl:when test="${empty row.paidMoment}">
			<jstl:out value="---" />
		</jstl:when>
		<jstl:otherwise>
			<fmt:formatDate value="${row.paidMoment}" pattern="dd/MM/yyyy HH:mm:ss" />
		</jstl:otherwise>
		</jstl:choose>
	</display:column> 
	<acme:column code="invoice.creationMoment" property="creationMoment" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
	<security:authorize access="hasRole('USER')">
	<display:column>
		<a href="book/user/display.do?bookId=${row.book.id}">
			<spring:message code="invoice.displayBook" />
		</a>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('MANAGER')">
	<display:column>
		<a href="book/manager/display.do?bookId=${row.book.id}">
			<spring:message code="invoice.displayBook" />
		</a>
	</display:column>
	
	<display:column>
		<jstl:if test="${empty row.paidMoment}">
			<form:form action="invoice/manager/markAsPaid.do?invoiceId=${row.id}" modelAttribute="invoice">
					<acme:submit name="paid" code="invoice.paid" />
				</form:form>
		</jstl:if>
	</display:column> 
	</security:authorize>
</display:table>

 <script>
	 var z=[];
	 var currencyColumn=0;
	 
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
