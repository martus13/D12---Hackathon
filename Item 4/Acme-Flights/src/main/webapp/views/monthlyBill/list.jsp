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

<display:table name="monthlyBills" id="row" requestURI="${requestURI}">
	
	<spring:message code="monthlyBill.creationMoment" var="creationMomentHeader" />
	<display:column property="creationMoment" title="${creationMomentHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
	
	<spring:message code="monthlyBill.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="true" />
	
	<spring:message code="monthlyBill.cost" var="costHeader" />
	<display:column property="cost" title="${costHeader}" sortable="true" />
	
	<spring:message code="monthlyBill.paidMoment" var="paidMomentHeader" />
	<display:column property="paidMoment" title="${paidMomentHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
	
	<security:authorize access="hasRole('SPONSOR')">
		<display:column>
			<a href="monthlyBill/sponsor/display.do?monthlyBillId=${row.id}" >
				<spring:message code="monthlyBill.display" />
			</a>
		</display:column>
		
		<display:column>
			<jstl:if test="${empty row.paidMoment}">
				<form:form action="monthlyBill/sponsor/pay.do?monthlyBillId=${row.id}" modelAttribute="monthlyBill">
					<input type="submit" name="pay" value="<spring:message code="monthlyBill.pay" />" />
				</form:form>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>
<security:authorize access="hasRole('ADMIN')">
	<form:form action="message/administrator/bulkMessage.do" modelAttribute="message">
		<input type="submit" name="bulkMessage" value="<spring:message code="monthlyBill.message.bulkMessage" />" />
	</form:form>
	<form:form action="monthlyBill/administrator/create.do" modelAttribute="monthlyBill">
		<input type="submit" name="monthlyBills" value="<spring:message code="monthlyBill.compute" />" />
	</form:form>
</security:authorize>
