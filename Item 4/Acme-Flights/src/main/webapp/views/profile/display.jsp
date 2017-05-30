<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<ul>
		<li>
			<b><spring:message code="profile.username" />:</b>
			<jstl:out value="${actor.userAccount.username}" />
		</li>
		
		<li>
			<b><spring:message code="profile.name" />:</b>
			<jstl:out value="${actor.name}" />
		</li>
		
		<li>
			<b><spring:message code="profile.surname" />:</b>
			<jstl:out value="${actor.surname }" />
		</li>
		
		<li>
			<b><spring:message code="profile.email" />:</b>
			<jstl:out value="${actor.email }" />
		</li>
		
		<li>
			<b><spring:message code="profile.phoneNumber" />:</b>
			<jstl:out value="${actor.contactPhone}" />
		</li>
		
		<jstl:if test="${isManager }">
			<li>
				<b><spring:message code="profile.airline" />:</b>
				<jstl:out value="${actor.airline.name}" />
			</li>
		</jstl:if>
		
	</ul>
	<a href="profile/actor/edit.do"><spring:message code="profile.edit" /></a>
</div>
