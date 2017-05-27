<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="offers" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="offer.startMoment" property="startMoment" />
	<acme:column code="offer.endMoment" property="endMoment" />
	<acme:column code="offer.discount" property="discount" />
	
	
	<display:column>
		<a href="offer/manager/edit.do?offerId=${row.id}">
			<spring:message code="offer.edit" />
		</a>
	</display:column>
	
	<display:column>
		<form:form action="offer/manager/delete.do?offerId=${row.id}" modelAttribute="offer">
			<acme:submit name="delete" code="offer.delete" />
		</form:form>
	</display:column>
	
</display:table>

<a href="offer/manager/create.do">
	<spring:message code="offer.create" />
</a>
