<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form method="post" action="finder/user/edit.do" modelAttribute="finder" >
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="user"/>
	<form:hidden path="results"/>
	
	
	<div>
			<form:label path="departure">
				<spring:message code="finder.departure" />
			</form:label>	
			<form:select path="departure">
				<form:option value="${null }" label="----" />		
				<jstl:forEach items="${airports }" var="departure">
					<form:option value="${departure}" label="${departure}" />
				</jstl:forEach>
			</form:select>
			<form:errors path="departure" cssClass="error" />
		</div>
		
		<div>
			<form:label path="destination">
				<spring:message code="finder.destination" />
			</form:label>	
			<form:select path="destination">
				<form:option value="${null }" label="----" />		
				<jstl:forEach items="${airports }" var="destination">
					<form:option value="${destination}" label="${destination}" />
				</jstl:forEach>
			</form:select>
			<form:errors path="destination" cssClass="error" />
		</div>
	
	<acme:input code="searchTemplate.approximateAge" path="approximateAge" />
	<acme:input code="searchTemplate.singleKeyword" path="singleKeyword" />
	<acme:input code="searchTemplate.country" path="country" />
	<acme:input code="searchTemplate.state" path="state" />
	<acme:input code="searchTemplate.province" path="province" />
	<acme:input code="searchTemplate.city" path="city" />
	
	<acme:submit name="save" code="searchTemplate.save" />
	<jstl:if test="${searchTemplate.id!=0 }">
		<acme:submit name="delete" code="searchTemplate.delete" />
	</jstl:if>
	
	<acme:cancel url="searchTemplate/chorbi/display.do" code="searchTemplate.cancel" />
	
</form:form>
	