<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authentication var="principalUserAccount" property="principal" />
<display:table name="books" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="book.creationMoment" property="creationMoment" format="{0,date,dd/MM/yyyy}" />
	<acme:column code="book.passengersNumber" property="passengersNumber" />
	<acme:column code="book.childrenNumber" property="childrenNumber" />
	<acme:column code="book.isBusiness" property="isBusiness" />
	<acme:column code="book.totalFee" property="totalFee" />
	<acme:column code="book.comment" property="comment" />
	<acme:column code="book.cancelationMoment" property="cancelationMoment" format="{0,date,dd/MM/yyyy}" />
	
	<display:column sortable="false">
		<a href="book/user/display.do?bookId=${row.id}">
			<spring:message code="book.display" />
		</a>
	</display:column>
	
</display:table>

