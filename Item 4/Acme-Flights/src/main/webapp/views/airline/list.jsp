<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="airlines" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:columnImages properties="picture" code="airline.picture" maxHeight="120px" />
	<acme:column code="airline.name" property="name" />
	<acme:column code="airline.contactPhone" property="contactPhone" />
	<acme:column code="airline.email" property="email" />
	<acme:column code="airline.rating" property="rating" />
	
	<display:column>
		<a href="airline/administrator/display.do?airlineId=${row.id }"><spring:message code="airline.display" /></a>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')" >
		<display:column>
			<a href="airline/administrator/edit.do?airlineId=${row.id }"><spring:message code="airline.edit" /></a>
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('ADMIN')" >
	<a href="airline/administrator/create.do"><spring:message code="airline.create" /></a>
</security:authorize>

