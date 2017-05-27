<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI }" modelAttribute="${registerForm }">
	
	<fieldset>
		<legend>
			<spring:message code="profile.userData" />
		</legend>
		<acme:input path="username" code="profile.username" />
		
		<acme:password path="password" code="profile.password" />
		
		<acme:password path="confirmPassword" code="profile.confirmPassword" />
		<br />
	</fieldset>
		
	<fieldset>
		<legend>
			<spring:message code="profile.personalData" />
		</legend>
		<acme:input path="name" code="profile.name" />
		
		<acme:input path="surname" code="profile.surname" />

		<acme:input path="email" code="profile.email" />
		
		<acme:input path="contactPhone" code="profile.phoneNumber" />
		
		<jstl:if test="${isManager }">
			<acme:select items="${airlines }" itemLabel="name" code="profile.airline" path="airline" />
		</jstl:if>
			
	</fieldset>
	<br>
	
	<acme:submit name="save" code="profile.save" />
	<acme:cancel url="welcome/index.do" code="profile.cancel" />
		
</form:form>
	
