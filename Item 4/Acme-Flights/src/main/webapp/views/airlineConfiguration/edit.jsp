<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form:form action="airlineConfiguration/manager/edit.do" modelAttribute="airlineConfiguration">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="airline"/>
	
	<acme:input code="airlineConfig.cancellationDays" path="maxCancellationDays"/>
	<acme:input code="airlineConfig.childrenAge" path="maxChildrenAge"/>
	<acme:input code="airlineConfig.childrenDiscount" path="childrenDiscount"/>
	<acme:input code="airlineConfig.maxBagWeight" path="maxBagWeight"/>
	<acme:input code="airlineConfig.overweightBagPrice" path="overweightBagPrice"/>
	
	<acme:submit name="save" code="airlineConfig.save"/>
	
</form:form>