<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
	
	<security:authorize access="hasRole('MANAGER')">
	<display:column>
		<jstl:if test="${empty row.paidMoment}">
			<form:form action="invoice/manager/markAsPaid.do?invoiceId=${row.id}" modelAttribute="invoice">
					<acme:submit name="paid" code="invoice.paid" />
				</form:form>
		</jstl:if>
	</display:column> 
	</security:authorize>
</display:table>

