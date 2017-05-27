<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="season/manager/create.do" modelAttribute="season">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="airline" />
	<form:hidden path="inactive" />
	
	<acme:input code="season.title" path="title" />
	<acme:input code="season.startDay" path="startDay" type="number" min="1" max="31" />
	<acme:input code="season.startMonth" path="startMonth" type="number" min="1" max="12" />
	<acme:input code="season.endDay" path="endDay" type="number" min="1" max="31" />
	<acme:input code="season.endMonth" path="endMonth" type="number" min="1" max="12" />
	
	<div>
		<form:label path="type">
			<spring:message code="season.type" />
		</form:label>	
		<form:select path="type">
			<form:option value="${null }" label="----" />		
			<form:option value="discount">
				<spring:message code="season.discount" />
			</form:option>
			<form:option value="increase">
				<spring:message code="season.increase" />
			</form:option>
		</form:select>
		<form:errors path="type" cssClass="error" />
	</div>
	
	<acme:input code="season.pricePercentage" path="pricePercentage" type="number" min="0" step="0.01" />
	
	<acme:submit name="save" code="season.save" />
	<acme:cancel url="season/manager/listByAirline.do" code="season.cancel" />
		
</form:form>
