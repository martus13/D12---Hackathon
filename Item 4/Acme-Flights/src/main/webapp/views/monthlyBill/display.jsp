<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
	<ul>
		<li>
			<b><spring:message code="monthlyBill.creationMoment" />:</b>
			<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${monthlyBill.creationMoment}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.sponsor" />:</b>
			<jstl:out value="${monthlyBill.campaigns[0].sponsor.name}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.description" />:</b>
			<jstl:out value="${monthlyBill.description}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.cost" />:</b>
			<jstl:out value="${monthlyBill.cost}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.paidMoment" />:</b>
			<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${monthlyBill.paidMoment}" />
		</li>
		
		<li>
			<b><spring:message code="monthlyBill.campaigns" />:</b><br />
			<display:table name="${monthlyBill.campaigns}" id="row">
			
				<spring:message code="monthlyBill.campaign.startDate" var="startDateHeader" />
				<display:column property="startDate" title="${startDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
				
				<spring:message code="monthlyBill.campaign.endDate" var="endDateHeader" />
				<display:column property="endDate" title="${endDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
			
				<spring:message code="monthlyBill.campaign.maxDisplayed" var="maxDisplayedHeader" />
				<display:column property="maxDisplayed" title="${maxDisplayedHeader}" sortable="true" />
				
				<spring:message code="monthlyBill.campaign.star" var="starHeader" />
				<display:column property="star" title="${starHeader}" sortable="true" />
				
				<spring:message code="monthlyBill.campaign.fee" var="feeHeader" />
				<display:column property="fee.cost" title="${feeHeader}" sortable="true" />
				
			</display:table>
		</li>
		
		
	</ul>
	
	<a href="monthlyBill/sponsor/list.do">
		<button><spring:message code="monthlyBill.goBack" /></button>
	</a>
</div>