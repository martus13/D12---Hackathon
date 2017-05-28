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

<display:table name="monthlyBills" id="row" requestURI="${requestURI }" >
	
	<acme:column code="monthlyBill.creationMoment" property="creationMoment" format="{0,date,dd/MM/yyyy}" />
	<acme:column code="monthlyBill.paidMoment" property="paidMoment" format="{0,date,dd/MM/yyyy}" />
	<acme:column code="monthlyBill.totalFee" property="totalFee" />
	<acme:column code="monthlyBill.description" property="description" />
		
</display:table>
<%-- <security:authorize access="hasRole('ADMIN')">
	<form:form action="message/administrator/bulkMessage.do" modelAttribute="message">
		<input type="submit" name="bulkMessage" value="<spring:message code="monthlyBill.message.bulkMessage" />" />
	</form:form>
	<form:form action="monthlyBill/administrator/create.do" modelAttribute="monthlyBill">
		<input type="submit" name="monthlyBills" value="<spring:message code="monthlyBill.compute" />" />
	</form:form>
</security:authorize>
 --%>