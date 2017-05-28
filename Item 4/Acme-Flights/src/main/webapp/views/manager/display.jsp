<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<security:authentication var="principalUserAccount" property="principal" />

<div>
	<ul>
		<li>
			<b><spring:message code="manager.name" />:</b>
			<jstl:out value="${manager.name}" />
		</li>
		
		<li>
			<b><spring:message code="manager.surname" />:</b>
			<jstl:out value="${manager.surname}" />
		</li>
		
		<li>
			<b><spring:message code="manager.email" />:</b>
			<jstl:out value="${manager.email}" />
		</li>
		
		<li>
			<b><spring:message code="manager.phoneNumber" />:</b>
			<jstl:out value="${manager.phoneNumber}" />
		</li>
		
		<li>
			<b><spring:message code="manager.airline" />:</b>
			<jstl:out value="${manager.company}" />
		</li>
		
	</ul>
</div>