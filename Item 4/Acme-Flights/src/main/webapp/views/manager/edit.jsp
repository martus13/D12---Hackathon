<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI }" modelAttribute="${userForm }">
	
	<fieldset>
		<legend>
			<spring:message code="manager.userData" />
		</legend>
		<acme:input path="username" code="manager.username" />
		
		<acme:password path="password" code="manager.password" />
		
		<acme:password path="confirmPassword" code="manager.confirmPassword" />
		<br />
	</fieldset>
		
	<fieldset>
		<legend>
			<spring:message code="manager.personalData" />
		</legend>
		<acme:input path="name" code="manager.name" />
		
		<acme:input path="surname" code="manager.surname" />

		<acme:input path="email" code="manager.email" />
		
		<acme:input path="contactPhone" code="manager.phoneNumber" />
			
	</fieldset>
	<br>
	
	<fieldset>
		<legend>
			<spring:message code="manager.managerData" />
		</legend>
		
		<form:label path="airline">
			<spring:message code="manager.airline" />
		</form:label>	
		<form:select path="airline">
			<form:option value="${null }" label="----" />		
			<jstl:forEach items="${airlines }" var="airline">
				<form:option value="${airline}" label="${airline.name}" />
			</jstl:forEach>
		</form:select>
		<form:errors path="airline" cssClass="error" />
		 
					
	</fieldset>
	<br>
	
	<acme:submit name="save" code="manager.save" />
	<acme:cancel url="welcome/index.do" code="manager.cancel" />
		
</form:form>
	
