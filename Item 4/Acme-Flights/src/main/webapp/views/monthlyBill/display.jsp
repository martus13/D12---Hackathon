<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<ul>
		<li>
			<b><spring:message code="monthlyBill.creationMoment" />:</b>
			<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${monthlyBill.creationMoment}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.airline" />:</b>
			<jstl:out value="${monthlyBill.airline.name}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.description" />:</b>
			<jstl:out value="${monthlyBill.description}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.totalFee" />:</b>
			<jstl:out value="${monthlyBill.totalFee}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.paidMoment" />:</b>
			<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${monthlyBill.paidMoment}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.campaigns" />:</b><br />
			<display:table name="${monthlyBill.campaigns}" id="row">
				
				<acme:column code="monthlyBill.campaign.name" property="name" />
				<acme:column code="monthlyBill.campaign.startDate" property="startDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
				<acme:column code="monthlyBill.campaign.endDate" property="endDate" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
				
			</display:table>
		</li>
		
		
	</ul>
</div>