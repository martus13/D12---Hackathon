<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="book/user/create.do" modelAttribute="book">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="creationMoment" />
	<form:hidden path="passengersNumber" />
	<form:hidden path="childrenNumber" />
	<form:hidden path="isBusiness" />
	<form:hidden path="originalPrice" />
	<form:hidden path="totalFee" />
	<form:hidden path="cancelationMoment" />
	<form:hidden path="user" />
	<form:hidden path="flights" />
	<form:hidden path="seasons" />
	<form:hidden path="offers" />
	
	<li>
		<b><spring:message code="book.passengersNumber" />:</b>
		<jstl:out value="${book.passengersNumber}" />
	</li>
	
	<li>
		<b><spring:message code="book.childrenNumber" />:</b>
		<jstl:out value="${book.childrenNumber}" />
	</li>
	
	<li>
		<b><spring:message code="book.isBusiness" />:</b>
		<jstl:choose>
			<jstl:when test="${book.isBusiness }">
				<spring:message code="book.yes" />
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="book.no" />
			</jstl:otherwise>
		</jstl:choose>
	</li>
	
	<li>
		<b><spring:message code="book.totalFee"/>:</b>
		<jstl:out value="${book.totalFee}"/>
	</li>
	
	<acme:textarea code="book.comment" path="comment" rows="3" />
	
	<acme:submit name="save" code="book.save" />
	<acme:cancel url="book/user/listByUser.do" code="book.cancel" />
		
</form:form>
