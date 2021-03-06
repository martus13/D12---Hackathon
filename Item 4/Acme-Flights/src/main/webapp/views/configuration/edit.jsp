<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form:form action="configuration/administrator/edit.do" modelAttribute="configuration">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	
	<acme:input code="configuration.fee" path="fee"/>
	<acme:input code="configuration.campaignFee" path="campaignFee"/>

	
	<acme:submit name="save" code="configuration.save"/>
	
</form:form>