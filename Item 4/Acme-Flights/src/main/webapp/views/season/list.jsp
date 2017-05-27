<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="seasons" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="season.title" property="title" />
	<acme:column code="season.startDay" property="startDay" />
	<acme:column code="season.startMonth" property="startMonth" />
	<acme:column code="season.endDay" property="endDay" />
	<acme:column code="season.endMonth" property="endMonth" />
	
	<spring:message code="season.type" var="typeHeader" />
	<display:column title="${typeHeader}" sortable="true">
		<jstl:choose>
			<jstl:when test="${row.type=='discount' }">
				<spring:message code="season.discount" />
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="season.increase" />
			</jstl:otherwise>
		</jstl:choose>
	</display:column>
	
	<acme:column code="season.pricePercentage" property="pricePercentage" />
	
	<security:authorize access="!hasRole('MANAGER')">
		<display:column>
			<a href="airline/display.do?airlineId=${row.airline.id }"><jstl:out value="${row.airline.name }" /></a>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<form:form action="season/manager/delete.do?seasonId=${row.id}" modelAttribute="season">
				<acme:submit name="delete" code="season.delete" />
			</form:form>
		</display:column>
	</security:authorize>
	
</display:table>
<security:authorize access="hasRole('MANAGER')">
	<a href="season/manager/create.do"><spring:message code="season.create" /></a>
</security:authorize>

