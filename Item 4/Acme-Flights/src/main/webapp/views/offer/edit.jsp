<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="offer/manager/edit.do" modelAttribute="offer">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="offertables"/>
	
	<acme:input code="offer.startMoment" path="startMoment" placeholder="dd/MM/yyyy HH:mm:ss"/>
	<acme:input code="offer.endMoment" path="endMoment" placeholder="dd/MM/yyyy HH:mm:ss"/>
	<acme:input code="offer.discount" path="discount" />
	
	<jstl:forEach items="${flights}" var="flight">
		<div class="form-group">
			<form:checkbox path="offertables" value="${flight}"/>
			<jstl:out value="${flight.id }" />
		</div>
	</jstl:forEach>
	<form:errors path="offertables" cssClass="error" />

	
	<acme:submit name="save" code="offer.save" />
	<acme:cancel url="offer/manager/list.do" code="offer.cancel" />
		
</form:form>
