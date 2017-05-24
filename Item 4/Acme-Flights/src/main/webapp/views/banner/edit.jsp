<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="banner/manager/edit.do" modelAttribute="banner">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="numDisplayed" />
	
	
	<acme:textarea code="banner.picture" path="picture" rows="3" />
	
	<form:label path="campaign">
			<spring:message code="banner.campaign" />
	</form:label>	
	<form:select path="campaign">
		<form:option value="${null }" label="----" />		
		<jstl:forEach items="${campaigns }" var="campaign">
			<form:option value="${campaign}" />
		</jstl:forEach>
	</form:select>
	<form:errors path="campaign" cssClass="error" />
		 
	<acme:submit name="save" code="banner.save" />
	<acme:cancel url="banner/manager/list.do" code="banner.cancel" />
		
</form:form>
