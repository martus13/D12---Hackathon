<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('MANAGER')">
	<a href="pointsCard/manager/resetExpired.do"><spring:message code="pointsCard.resetExpired" /></a>
</security:authorize>

<display:table name="pointsCards" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<security:authorize access="hasRole('MANAGER')">
		<acme:column code="pointsCard.user" property="user.name" />
	</security:authorize>
	
	<acme:column code="pointsCard.expirationMoment" property="expirationMoment" format="{0,date,dd/MM/yyyy}" />
	<acme:column code="pointsCard.points" property="points" />
	<acme:column code="pointsCard.airline" property="airline.name" />
	
</display:table>

