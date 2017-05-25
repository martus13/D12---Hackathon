<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="airline/administrator/edit.do" modelAttribute="airline">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="rating" />
	<form:hidden path="deleted" />
	<form:hidden path="seasons" />
	
	<acme:input code="airline.name" path="name" />
	<acme:textarea code="airline.picture" path="picture" />
	<acme:input code="airline.contactPhone" path="contactPhone" />
	<acme:input code="airline.email" path="email" />
		 
	<acme:submit name="save" code="airline.save" />
	<jstl:if test="${airline.id!=0 }">
		<acme:submit name="delete" code="airline.delete" />
	</jstl:if>
	<acme:cancel url="airline/administrator/list.do" code="airline.cancel" />
		
</form:form>