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
	<form:hidden path="numDisplayedBilled" />
	
	
	<acme:textarea code="banner.picture" path="picture" rows="3" />
	<acme:select items="${campaigns}" itemLabel="name" code="banner.campaign" path="campaign" />
	
	<acme:submit name="save" code="banner.save" />
	<acme:cancel url="banner/manager/list.do" code="banner.cancel" />
		
</form:form>
